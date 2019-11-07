package com.tibaes.fornecedores.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.tibaes.fornecedores.R
import kotlinx.android.synthetic.main.activity_confirm.*



class ConfirmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        //tornar o botão de voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // recebe a intent da tela anterior
        val intent =  intent
        val provider = intent.getStringExtra("provider_key")

        // altera o valor do texto para o valor recebido da tela anteior
        txtResult.text = provider
    }

    // define as ações dos botões do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId == android.R.id.home){
            // fecha a atividade
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }

    fun makePhoneCall(number: String) : Boolean{
        try {
            // preparar a intent com o número do telefone
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            // iniciar a atividade
            startActivity(intent)
            return true
        } catch (e: Exception){
            // ver o erro
            e.printStackTrace()
            return false
        }
    }

    fun sendSMS(number: String) : Boolean{
        try {
            // preparar a intent com o número do telefone
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.fromParts("sms", number, null))
            // iniciar a atividade
            startActivity(intent)
            return true
        } catch (e: Exception){
            // ver o erro
            e.printStackTrace()
            return false
        }
    }

    fun sendEmail(email: String) : Boolean{
        try {
            // preparar a intent com o email de destinatário
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:$email"))
            // iniciar a atividade
            startActivity(intent)
            return true
        } catch (e: Exception){
            // ver o erro
            e.printStackTrace()
            return false
        }
    }

    fun openSite(site: String) : Boolean{
        try {
            // preparar a intent com o site que será aberto
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(site))
            // iniciar a atividade
            startActivity(intent)
            return true
        } catch (e: Exception){
            // ver o erro
            e.printStackTrace()
            return false
        }
    }
}
