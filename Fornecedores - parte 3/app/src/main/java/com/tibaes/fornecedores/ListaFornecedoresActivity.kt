package com.tibaes.fornecedores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lista_fornecedores.*

class ListaFornecedoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_fornecedores)

        var fornecedor = Fornecedor(
            "Juliana",
            "tibaes@up.edu.br",
            "999-222")

        var fornecedor_list = listOf<Fornecedor>(
            fornecedor,
            Fornecedor("Juliana", "mail@com.bf", "99", 2F),
            Fornecedor("Juliana", "mail@com.bf", "99", 2F),
            Fornecedor("Juliana", "mail@com.bf", "99", 2F),
            Fornecedor("Juliana", "mail@com.bf", "99", 2F)
            )


    }
}











