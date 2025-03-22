package com.example.pfm.Repository

import com.example.pfm.Domain.ExpenseDomain

class MainRepository {
    val items= mutableListOf(
        ExpenseDomain("Mua sắm",300000.0 ,"img1" ,"22/03/2025 15:00"),
        ExpenseDomain("Tiền ăn",900000.0 ,"im2" ,"22/03/2025 16:00"),
        ExpenseDomain("Xem phim",90000.0 ,"img3" ,"22/03/2025 17:00"),
    )
}