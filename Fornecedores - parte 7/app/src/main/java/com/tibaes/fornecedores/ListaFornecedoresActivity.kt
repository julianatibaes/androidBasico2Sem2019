package com.tibaes.fornecedores

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tibaes.fornecedores.db.Fornecedor
import kotlinx.android.synthetic.main.activity_lista_fornecedores.*
import java.lang.Exception

class ListaFornecedoresActivity : AppCompatActivity() {

    // variável imutavel para enviar o código de request do cadastro
    val REQUEST_CODE = 12

    // objeto da view model que será instanciado posteriormente
    private lateinit var fornecedorViewModel: FornecedorViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_fornecedores)

        recyclerView = recycler_fornecedores

        montaLista(recyclerView)

        fbNew.setOnClickListener {
            val intet = Intent(this, MainActivity::class.java)

            // abre a activity, porém aguarda o resultado com o objeto par salvar
            startActivityForResult(intet, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // caso o código enviado e o código recebido sejam os esperados, verificar o dado recebido
        if(requestCode == REQUEST_CODE &&
            resultCode == Activity.RESULT_OK){
            // caso o valor recebido seja diferente de nulo, executa o bloco de ação
            data?.let { resultado ->
                try{

                    // recebe o objeto da intent, faz o cast para o objeto Fornecedor
                    val fornecedor: Fornecedor = resultado.extras?.
                            get(MainActivity.EXTRA_REPLY) as Fornecedor

                    // caso o objeto seja diferente de vazio, envia para o viewmodel
                    fornecedor.let {
                        fornecedorViewModel.insert(fornecedor)
                    }
                } catch (e: Exception){
                    Log.d("TAG: ", e.message)
                }
            }
        }

    }

    private fun montaLista(recyclerView: RecyclerView){

        val adapter = ListaFornecedorAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        fornecedorViewModel = ViewModelProviders.of(this).
            get(FornecedorViewModel::class.java)


        fornecedorViewModel.fornecedores.observe(this,
            Observer { fornecedorLista ->
                fornecedorLista?.let { lista ->
                    adapter.setFornecedorLista(lista)
                }
            })
    }

    override fun onRestart() {
        super.onRestart()

        montaLista(recyclerView)

    }
}











