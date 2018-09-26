package com.itbam.mercadinhox.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.itbam.mercadinhox.R
import com.itbam.mercadinhox.model.Product
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

//      Inicializando a toolbar
        val toolbar = ap_toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

//      Recebendo o objeto produto proveniente da activity anterior
        val product = intent.getParcelableExtra<Product>("product")

        ap_iv_product.setImageBitmap(product.image)

//      Fazendo a interpolação das strings para formar o nome + peso do produto
        ap_tv_name.text = StringBuilder().append(product.name.toLowerCase().capitalize()).append(" ").append("%.2f".format(product.weight)).append("g")

        ap_tv_description.text = product.description

//      Setando o scroll na textView
        ap_tv_description.movementMethod = ScrollingMovementMethod()

        ap_bt_back.setOnClickListener {
            finish()
        }
    }

    /**
     * Tratando o clique no botão "físico" de voltar
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
