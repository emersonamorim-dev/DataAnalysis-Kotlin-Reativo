package com.applications.DataAnalysis.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("products")
data class Data(
    @PrimaryKey val Id: String,
    val productName: String,
    val productPrice: Double,
    val quantitySold: Int
)
