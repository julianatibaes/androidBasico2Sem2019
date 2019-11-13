package com.tibaes.fornecedores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tibaes.fornecedores.db.Fornecedor
import kotlinx.android.synthetic.main.item_lista.view.*

class ListaFornecedorAdapter
internal constructor(context: Context) :
    RecyclerView.Adapter<ListaFornecedorAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    //private val itens = lista()

    //novo - itens que virão do banco de dados
    private var itens = emptyList<Fornecedor>()

    // infla o layout do item da lista para cada componente da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(
            R.layout.item_lista,
            parent,
            false
        )
        return ViewHolder(view)

    }

    // retorna o tamanho da lista
    override fun getItemCount(): Int = itens.count()

    // colocando os itens da lista nos itens de view da lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itens[position]
        holder.itemNome.text = item.nome
        holder.itemEmail.text = item.email
        holder.itemClassicacao.rating = item.classificacao
    }

    // Classe para mapear os componentes do item da lista
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val itemNome: TextView = itemView.itemNome
        val itemEmail: TextView = itemView.itemEmail
        val itemClassicacao: RatingBar = itemView.itemRB
    }

    /*private fun lista(): List<Fornecedor>{
        return listOf(
            Fornecedor("Juliana", "mail@com.bf", "99", 2F),
            Fornecedor("Juliana", "mail@com.bf", "99", 3F),
            Fornecedor("Juliana", "mail@com.bf", "99", 4F),
            Fornecedor("Juliana", "mail@com.bf", "99", 5F)
        )
        }
     */

    // método para atualizar os dados da lista
    fun setFornecedorLista(fornecedores: List<Fornecedor>) {
        this.itens = fornecedores
        notifyDataSetChanged()
    }
}
