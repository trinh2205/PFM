package com.example.mainproject.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mainproject.viewModel.AppViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class AuthState(
    val signInState: SignInState = SignInState(),
    val signUpState: SignUpState = SignUpState()
    // Thêm các trạng thái khác liên quan đến xác thực (ví dụ: quên mật khẩu)
)

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val firebaseUser: FirebaseUser? = null // Lưu trữ FirebaseUser sau khi đăng nhập thành công
)

data class SignUpState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val firebaseUser: FirebaseUser? = null // Lưu trữ FirebaseUser sau khi đăng ký thành công
)

class AuthViewModel(private val appViewModel: AppViewModel) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("users")

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.update { it.copy(signInState = SignInState(isLoading = true)) }
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                val firebaseUser = result.user
                _authState.update { it.copy(signInState = SignInState(isSuccess = true, firebaseUser = firebaseUser)) }
                Log.d("AuthViewModel", "Đăng nhập thành công: ${firebaseUser?.uid}")
                // Sau khi đăng nhập thành công, lấy thông tin người dùng từ Firestore và cập nhật AppViewModel
                appViewModel.fetchCurrentUser(firebaseUser)
            } catch (e: Exception) {
                _authState.update { it.copy(signInState = SignInState(errorMessage = e.localizedMessage ?: "Lỗi không xác định")) }
                Log.e("AuthViewModel", "Đăng nhập thất bại: ${e.localizedMessage}")
            }
        }
    }

    fun signUp(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            _authState.update { it.copy(signUpState = SignUpState(isLoading = true)) }
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val firebaseUser = result.user
                _authState.update { it.copy(signUpState = SignUpState(isSuccess = true, firebaseUser = firebaseUser)) }
                Log.d("AuthViewModel", "Đăng ký thành công với UID: ${firebaseUser?.uid}")

                // Cập nhật displayName cho người dùng
                firebaseUser?.updateProfile(
                    com.google.firebase.auth.UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                )?.await()

                // Lưu thông tin người dùng bổ sung vào Firestore
                firebaseUser?.uid?.let { uid ->
                    val userMap = hashMapOf(
                        "email" to email,
                        "displayName" to displayName
                        // Thêm các trường thông tin khác nếu cần
                    )
                    usersCollection.document(uid).set(userMap).await()
                }

                // Gửi email xác minh
                firebaseUser?.sendEmailVerification()?.await()
                Log.d("AuthViewModel", "Đã gửi email xác minh.")

                // Sau khi đăng ký thành công, lấy thông tin người dùng và cập nhật AppViewModel
                appViewModel.fetchCurrentUser(firebaseUser)

            } catch (e: Exception) {
                _authState.update { it.copy(signUpState = SignUpState(errorMessage = e.localizedMessage ?: "Lỗi không xác định")) }
                Log.e("AuthViewModel", "Đăng ký thất bại: ${e.localizedMessage}")
            }
        }
    }

    // Thêm các hàm khác cho quên mật khẩu, đăng xuất, v.v. (nếu cần)
}