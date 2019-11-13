package com.up.estrelas.ui

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.up.estrelas.R
import com.up.estrelas.adapter.EstrelaRecyclerAdapter
import com.up.estrelas.entity.Estrela
import com.up.estrelas.viewmodel.EstrelaViewModel
import kotlinx.android.synthetic.main.activity_lista_estrela.*
import java.lang.Exception

class ListaEstrelaActivity : AppCompatActivity() {

    private lateinit var estrelaViewModel: EstrelaViewModel
    lateinit var notificationManager: NotificationManagerCompat

    val REQUEST_CODE = 12
    val REQUEST_CODE_UPDATE = 13
    val CHANNEL_ID = "com.up.estrelas.ui.CHANNEL_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_estrela)

        notificationManager = NotificationManagerCompat.from(this)

        val recyclerView_: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        montaLista(recyclerView_)

        btnNovo.setOnClickListener {
            val intent = Intent(this,
                EstrelaActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)

           /* toast com mais detalhes
           val mensagem = "Toast \n funcionando"
           val duracao = Toast.LENGTH_LONG
           val toast = Toast.makeText(
                this,
                mensagem,
                duracao)
            toast.setGravity(Gravity.CENTER,
                0,
                0)
            toast.show()
            */

        }
    }

    private fun montaLista(recyclerView_: RecyclerView){
        recyclerView_.setHasFixedSize(true)
        // responsável por medir e posicionar as visualizações dos itens
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)

        recyclerView_.layoutManager = layoutManager

        val adapter = EstrelaRecyclerAdapter()

        adapter.onItemClickListener = {it ->
            val intent = Intent(this@ListaEstrelaActivity,
                EstrelaActivity::class.java)
            intent.putExtra(EstrelaActivity.EXTRA_REPLY, it)
            startActivityForResult(intent, REQUEST_CODE_UPDATE)
        }

        recyclerView_.adapter = adapter

        estrelaViewModel = ViewModelProviders.of(this).
            get(EstrelaViewModel::class.java)

        // recebe todos os dados do banco e atualiza no adapter
        estrelaViewModel.allStar.observe(this, Observer { stars ->
            stars?.let {
                adapter.setStarList(it)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE &&
            resultCode == Activity.RESULT_OK) {
            data?.let { resultado ->
                try{
                    val estrela = resultado.extras?.
                        get(EstrelaActivity.EXTRA_REPLY) as Estrela
                    estrela.let {
                        estrelaViewModel.insert(estrela)
                    }

                } catch (e: Exception){
                    Log.d("TAG: ", e.message)
                }

            }
        } else if (requestCode == REQUEST_CODE_UPDATE &&
            resultCode == Activity.RESULT_OK) {
            data?.let { resultado ->
                try {
                    var estrela: Estrela? = resultado.extras?.
                        get(EstrelaActivity.EXTRA_REPLY) as? Estrela

                    if (estrela == null){
                        estrela = resultado.extras?.
                            get(EstrelaActivity.EXTRA_DELETE) as? Estrela
                        estrela.let {
                            // mostra notifcação
                            showNotification("Excluída", it!!.descricao)
                            // exclui objeto
                            estrelaViewModel.delete(it)
                        }
                    } else
                    estrela.let { estrelaViewModel.update(estrela) }

                } catch (e: Exception) {
                    Log.d("TAG: ", e.message)
                }

            }
        }
    }

    // cria notificação
    private fun showNotification(titulo: String, conteudo: String){
        val notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_delete)
                .setContentTitle(titulo)
                .setContentText(conteudo)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
        notificationManager.notify(1, notification)

    }
}
    /*private fun lista(): ArrayList<Estrela>{
        return arrayListOf(
            Estrela(descricao = "sol", tamanho = 23F),
            Estrela(tamanho = 23F, descricao = "Gamma"),
            Estrela(descricao = "Sirus", tamanho = 3423F)
        )
    } */

