package com.tibaes.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnResultado.setOnClickListener {

            if (!etPrimeiroValor.text.toString().equals("")){
                // recebe o valor do campo de texto editável
                val primeiroValor = etPrimeiroValor.text.toString().toInt()
                val segundoValor = sbSegundoValor.progress
                //refatoração da ação do método de calcular para a ação do botão
                // mostra o resultado no campo de texto
                txtResultado.text = calcula(primeiroValor, segundoValor)
            }
            else txtResultado.text = getString(R.string.mensagem_erro_resulado)

            // altera para vazio o campo de texto editável
            etPrimeiroValor.setText("")
            // altera para 1 o valor do progresso
            sbSegundoValor.progress = 1

        }

        // Usando o listening do seekbar para alterar o valor do campo de texto
        sbSegundoValor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // campo de texto recebe o valor do progresso enquanto o progresso está ocorrendo
                txtValorProgress.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }

    private fun calcula(primeiroValor: Int, segundoValor: Int): String {

            val resultado: Int

            // Tratamento das operações
            if (rbSoma.isChecked) {
                resultado = primeiroValor + segundoValor
            } else if (rbSubtrai.isChecked) {
                resultado = primeiroValor - segundoValor
            } else if (rbMultiplica.isChecked) {
                resultado = primeiroValor * segundoValor
            } else if (rbDivide.isChecked && segundoValor != 0) {
                resultado = primeiroValor / segundoValor
            } else {
                resultado = 0
            }
            return resultado.toString()
    }

    fun mostraResultado(view: View){
        Toast.makeText(
            this,
            "Nunca use texto direto no código!",
            Toast.LENGTH_LONG
        ).show()
    }
}
