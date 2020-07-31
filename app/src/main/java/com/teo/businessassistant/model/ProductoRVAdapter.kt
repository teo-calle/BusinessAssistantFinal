package com.teo.businessassistant.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.businessassistant.R
import kotlinx.android.synthetic.main.fragment_nuevoproducto.view.*
import kotlinx.android.synthetic.main.inventario_item.view.*

class ProductoRVAdapter (
    var ProductoList: ArrayList<Producto>
): RecyclerView.Adapter<ProductoRVAdapter.ProductoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoRVAdapter.ProductoViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.inventario_item,parent,false)
        return ProductoRVAdapter.ProductoViewHolder(itemView)
    }
    override fun getItemCount(): Int = ProductoList.size

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val Productol: Producto = ProductoList[position]
        holder.bindProducto(Productol)
    }

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindProducto(Producto: Producto) {
            itemView.tv_nombre_producto.text = Producto.nombre_elemento
            itemView.tv_unidades.text = Producto.unidades.toString()
            itemView.tv_precio.text = Producto.precio.toString()
            if (Producto.nombre_elemento.isNullOrEmpty())
                itemView.tv_nombre_producto.text = "Esta vacio"
         }
    }

}