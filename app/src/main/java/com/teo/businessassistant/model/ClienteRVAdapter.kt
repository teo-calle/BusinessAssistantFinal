package com.teo.businessassistant.model
import  android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.businessassistant.R
import kotlinx.android.synthetic.main.clientes_item.view.*

class ClienteRVAdapter (
    var clienteList: ArrayList<Cliente>
) : RecyclerView.Adapter<ClienteRVAdapter.ClienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.clientes_item,parent,false)
        return ClienteViewHolder(itemView)
    }
    override fun getItemCount(): Int = clienteList.size

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val Cliente: Cliente = clienteList[position]
        holder.bindCliente(Cliente)
    }
    class ClienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCliente(Cliente: Cliente) {
            itemView.tv_nombrec.text = Cliente.nombre_cliente
            itemView.tv_cantidad.text = Cliente.celular_cliente
            if (Cliente.nombre_cliente.isNullOrEmpty())
                itemView.tv_nombrec.text = "Esta vacio"
        }
    }
}
