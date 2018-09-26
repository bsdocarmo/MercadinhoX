package com.itbam.mercadinhox.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

data class Data(
        val errors: Any,
        val products: ArrayList<Product>)

@Parcelize
data class Product(
        val preservationFreezer: Int,
        val energeticValue: String,
        val totalFat: String,
        val quantity: Int,
        val productId: Int,
        val description: String,
        val weight: Float,
        val priceMinimum: Float,
        val enabled: Boolean,
        val url: String,
        val createdAt: Long,
        val size: String,
        val productCod: String,
        val preservationRefrigerator: Int,
        val priceSuggested: Float,
        val protein: String,
        val name: String,

        var image: Bitmap) : Parcelable {

    @IgnoredOnParcel
    var pricePromotional: Any? = null
        set(value) {
            field = value ?: 0.0f
        }
}