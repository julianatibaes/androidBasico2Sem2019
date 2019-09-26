package com.tibaes.listmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter internal constructor(context: Context) :
RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items = lista() // cachear os elementos

    // infla o layout do item da lista para cada componente da lista
    override fun onCreateViewHolder(holder: ViewGroup, position: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_item, holder,
            false )
        return ViewHolder(view)
    }

    // retorna o tamanho da lista
    override fun getItemCount() = items.size

    // colocando os itens da lista nos itens de view da lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = items[position]
        holder.itemName.text = current.name
        holder.itemPhone.text = current.phone
        holder.itemClassification.rating = current.classification
    }

    // classe para mapear os componentes do item da lista
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val itemName: TextView = itemView.itemName
        val itemPhone: TextView = itemView.itemPhone
        val itemClassification: RatingBar = itemView.itemRBClassification
    }

    // lista temporária com os dados a serem trabalhados
    private fun lista(): List<Item>{
         return listOf(
             Item("Ana", "999-999", 2F),
             Item("Ana", "999-999", 2F),
             Item("Ana", "999-999", 2F)
         )
     }
}