package com.tibaes.fornecedores.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.tibaes.fornecedores.R
import kotlinx.android.synthetic.main.activity_confirm.*
import makePhoneCall
import openSite
import sendEmail
import sendSMS

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

    // abrir uma url
    fun openSite(view: View){
        openSite(txtUrl.text.toString())
    }

    // envia email
    fun sendEmail(view: View){
        sendEmail(txtEmail.text.toString())
    }

    // envia sms - precisa de permissão
    fun sendSMS(view: View){
        sendSMS(txtPhone.text.toString())
    }

    // abre ára fazer chamada - precisa de permissão
    fun makePhoneCall(view: View){
        makePhoneCall(txtPhone.text.toString())
    }
}
