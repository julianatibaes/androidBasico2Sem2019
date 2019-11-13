package com.tibaes.fornecedores

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tibaes.fornecedores.db.Fornecedor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // variável para trabalhar com o objeto do domínio
    lateinit var fornecedor: Fornecedor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Script do botão de salvar
    fun save(view: View){

        if(edtContact.text.isNullOrEmpty())
            Toast.makeText(
                this,
                "O nome do fornecedor não pode ser vazio",
                Toast.LENGTH_LONG
            ).show()
        else{
            // instacia o objeto vazio
            fornecedor = Fornecedor()
            popObj()

            var intent = Intent()
            // envia o objeto com a chave da constante
            intent.putExtra(EXTRA_REPLY, fornecedor)

            // envia o resultado da montagem do objeto para  intent
            setResult(Activity.RESULT_OK, intent)

            // fecha a activity
            finish()
        }

    }

    // popula todos os elementos da view no objeto
    private fun popObj(){
        fornecedor.nome = edtContact.text.toString()
        fornecedor.email = edtEmail.text.toString()
        fornecedor.telefone = edtPhone.text.toString()
        fornecedor.classificacao = rbClassification.progress.toFloat()
    }

    // constate para acessar de qualquer classe do aplicaivo
    companion object {
        const val EXTRA_REPLY = "com.tibaes.fornecedores.REPLY"
    }
}
