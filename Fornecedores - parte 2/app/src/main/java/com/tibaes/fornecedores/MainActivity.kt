package com.tibaes.fornecedores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.tibaes.fornecedores.ui.ConfirmActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista com os dados que vão para o spinner
        val activity_list = arrayOf(
            "",
            "Vestuário",
            "Bebidas",
            "Locais",
            "Alimentos",
            "Som",
            "Iluminação")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            activity_list)

        //Adicionar o modelo dos dados com os dados no adapter do spinner
        sActing.adapter = adapter

    }

    // Script do botão de salvar
    fun save(view: View){

        // Adicionando os dados do contato do fornecedor
        var provider = edtContact.text.toString()
        provider = provider + "\n" + edtEmail.text.toString()
        provider = provider + "\n" + edtPhone.text.toString()

        // Caso esteja clicado, adiciona ao texto o valor do check box
        if (cbCreditCard.isChecked)
            provider = provider + "\n" + cbCreditCard.text.toString()
        if (cbDebitCard.isChecked)
            provider = provider + "\n" + cbDebitCard.text.toString()
        if (cbPaypal.isChecked)
            provider = provider + "\n" + cbPaypal.text.toString()

        // Pegar o valor do item do spinner selecionado
        provider = provider + "\n" + sActing.selectedItem.toString()

        // Adiciona ativo ou inativo dependendo do switch
        if (sActive.isChecked)
            provider = provider + "\n" + "Ativo"
        else
            provider = provider + "\n" + "Inativo"

        // Preparar os dados para abrir na próxima tela
        val intent = Intent(this, ConfirmActivity::class.java )
        intent.putExtra("provider_key", provider)
        startActivity(intent)
    }
}
