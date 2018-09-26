package com.itbam.mercadinhox.retrofit

import com.itbam.mercadinhox.retrofit.service.ProductService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Inicializando o framework Retrofit
class RetrofitInitializer {

    val productService: ProductService
        get() = retrofit.create(ProductService::class.java)

    private var client: OkHttpClient.Builder

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)

    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
}