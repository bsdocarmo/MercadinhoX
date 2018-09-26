package com.itbam.mercadinhox.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.itbam.mercadinhox.R
import com.itbam.mercadinhox.model.Product

class ProductAdapter(private val context: Context, private val dataSet: ArrayList<Product>) :
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    //  Fornece uma referencia das views do layout para cada item do dataSet
    class ProductViewHolder internal constructor(itemView: ConstraintLayout) : RecyclerView.ViewHolder(itemView) {

        var name: TextView = itemView.findViewById(R.id.ip_tv_name)
        var image: ImageView = itemView.findViewById(R.id.ip_iv_product)
        var price: TextView = itemView.findViewById(R.id.ip_tv_price)
        var addCart: Button = itemView.findViewById(R.id.ip_bt_add_cart)

    }

    //  Cria as views
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProductViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_product, parent, false) as ConstraintLayout

        return ProductViewHolder(itemView)
    }

    //  Coloca o conteudo nas views
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (!dataSet[position].image.sameAs(BitmapFactory.decodeResource(context.resources,
                        R.drawable.ampulheta))) {
            holder.image.setImageBitmap(dataSet[position].image as Bitmap?)
            holder.image.scaleType = ImageView.ScaleType.FIT_XY
        }

        holder.name.text = StringBuilder().append(dataSet[position].name.toLowerCase().capitalize()).append(" ").append("%.2f".format(dataSet[position].weight)).append("g")
        holder.price.text = StringBuilder().append("R$").append("%.2f".format(dataSet[position].priceSuggested))

        holder.image.setOnClickListener {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("product", dataSet[position])

            context.startActivity(intent)
        }

        holder.addCart.setOnClickListener {
            Toast.makeText(context, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show()
        }
    }

    //  Retorna o tamanho do dataSet
    override fun getItemCount() = dataSet.size
}