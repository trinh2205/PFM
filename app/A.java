 <ImageView
        android:id="@+id/imageIntro"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scaleType="centerCrop"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="1.0"
        app:srcCompat="@drawable/intro_pic" />

    <TextView
        android:id="@+id/startBin"
        android:layout_width="135dp"
        android:layout_height="50dp"
        android:layout_marginStart="32dp"
        android:layout_marginBottom="32dp"
        android:background="@drawable/stroke_bg"
        android:text="Get Started"
        android:gravity="center"
        android:textColor="@color/white"
        android:textSize="20sp"
        app:layout_constraintBottom_toBottomOf="@+id/imageIntro"
        app:layout_constraintStart_toStartOf="@+id/imageIntro" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="32dp"
        android:text="The Best App Yo\n
Management Your\n Money"
        android:textSize="35sp"
        android:textStyle="bold"
        android:textColor="@color/white"
        app:layout_constraintBottom_toTopOf="@+id/startBin"
        app:layout_constraintStart_toStartOf="@+id/startBin" />