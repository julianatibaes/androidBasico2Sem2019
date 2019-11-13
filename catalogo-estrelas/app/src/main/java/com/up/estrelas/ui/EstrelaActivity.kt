package com.up.estrelas.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.up.estrelas.R
import com.up.estrelas.entity.Estrela
import kotlinx.android.synthetic.main.activity_estrela.*

class EstrelaActivity : AppCompatActivity() {

    lateinit var estrela: Estrela

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estrela)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // ======= [ receber os dados da intent caso já exista o objeto]

        val intent = intent
        try {
            estrela = intent.extras.get(EXTRA_REPLY) as Estrela
            estrela.let {
                etDescricao.setText(it.descricao)
                etDiscancia.setText(it.distnciaTerra.toString())
                etamanho.setText(it.tamanho.toString())
                etCor.setText(it.cor)
            }
        } catch (e: Exception){
            Log.e("TAG: ", e.message)
        }
        // ======= [ receber os dados da intent caso já exista o objeto]


    }

    private fun populaObjeto() {
        estrela.descricao = etDescricao.text.toString()

        if (etDiscancia.text.isNotEmpty())
            estrela.distnciaTerra = etDiscancia.text.toString().toFloat()

        if (etamanho.text.isNotEmpty())
            estrela.tamanho = etamanho.text.toString().toFloat()

        if (etCor.text.isNotEmpty())
            estrela.cor = etCor.text.toString()
    }


    // ==== [begin menu] ==========

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_projeto, menu)
        try {
            estrela.let {
                val menuItem = menu?.findItem(R.id.menu_excluir)
                menuItem?.isVisible = true
            }
        } catch (e: Exception){
            Log.e("TAG: ", "novo item " + e.message)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return if(item?.itemId == android.R.id.home){
            finish()
            true
        } else if (item?.itemId == R.id.menu_save){
            val retornaIntent = Intent()

            if(etDescricao.text.isNullOrEmpty())
                Toast.makeText(this, "A descrição não pode ser" +
                        "vazia", Toast.LENGTH_LONG)
                    .show()
            else{
                try {
                    if((::estrela.isInitialized) && estrela.id > 0){
                        populaObjeto()

                    }
                    else{
                        estrela = Estrela()
                        populaObjeto()
                    }
                    retornaIntent.putExtra(EXTRA_REPLY, estrela)
                }catch (e: Exception){
                    setResult(Activity.RESULT_CANCELED, retornaIntent)
                }
                setResult(Activity.RESULT_OK, retornaIntent)
                finish()
            }
            true
        } else if (item?.itemId == R.id.menu_excluir) {
            if((::estrela.isInitialized) && estrela.id > 0) {
                val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_DELETE, estrela)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
            true
        } else
            return super.onOptionsItemSelected(item)
    }

    // ==== [end menu] ============

    companion object {
        const val EXTRA_REPLY = "com.up.estrelas.ui.REPLY"
        const val EXTRA_DELETE = "com.up.estrelas.ui.DELETE"
    }
}
