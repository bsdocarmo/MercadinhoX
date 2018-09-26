package com.itbam.mercadinhox.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.itbam.mercadinhox.R
import com.itbam.mercadinhox.model.Product
import com.itbam.mercadinhox.retrofit.RetrofitInitializer
import com.itbam.mercadinhox.retrofit.dto.ProductSync
import com.itbam.mercadinhox.retrofit.urlBase
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      Inicializando a toolbar
        val toolbar = am_toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onResume() {
        super.onResume()

        val recyclerView = am_rv_products

        productRequest(recyclerView)
    }

    /**
     * Faz requisição dos produtos para o servidor
     */
    private fun productRequest(recyclerView: RecyclerView) {
        val call = RetrofitInitializer().productService.list()
        call.enqueue(object : Callback<ProductSync> {
            override fun onResponse(call: Call<ProductSync>, response: Response<ProductSync>) {
                response.body()?.let {
                    val productSync = it

                    val products = productSync.data.products

//                  Configura a recyclerView para receber os dados coletados do servidor
                    configureList(recyclerView, products)

//                  Percorre a lista de produtos e faz a requisição de suas imagens
                    for (product in products) {
                        product.image = BitmapFactory.decodeResource(resources,
                                R.drawable.ampulheta)

                        imageRequest(recyclerView, product)
                    }
                }
            }

            override fun onFailure(call: Call<ProductSync>, t: Throwable) {
                Log.e("onFailure error", t.message)

            }
        })
    }

    /**
     * Faz a requisiçao das imagem do produto para o servidor
     */
    private fun imageRequest(recyclerView: RecyclerView, product: Product) {
        val call = RetrofitInitializer().productService.image(product.url.removePrefix(urlBase))
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.let {
                    val bytes = it.bytes()

                    product.image = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                    runOnUiThread { (recyclerView.adapter as ProductAdapter).notifyDataSetChanged() }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }
        })
    }

    //  Inicializa a recyclerView
    private fun configureList(recyclerView: RecyclerView, products: ArrayList<Product>) {
        recyclerView.adapter = ProductAdapter(this, products)

        val layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }
}
