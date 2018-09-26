package com.itbam.mercadinhox.retrofit.service

import com.itbam.mercadinhox.retrofit.dto.ProductSync
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ProductService {

    //Requisicao para pegar a lista de produtos
    @GET("lista/")
    fun list(): Call<ProductSync>

    //Requisicao para pegar a imagem do produto
    @GET
    fun image(@Url url: String): Call<ResponseBody>
}