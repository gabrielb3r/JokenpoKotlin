package com.example.joken

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.util.Random

class MainActivity : AppCompatActivity() {

    var selecaoImageView: ImageView? = null
    val random = Random()
    var placarUsuario = 0
    var placarCPU = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pedraBtn = findViewById<ImageView>(R.id.pedraBtn)
        val papelBtn = findViewById<ImageView>(R.id.papelBtn)
        val tesouraBtn = findViewById<ImageView>(R.id.tesouraBtn)
        val jogarBtn = findViewById<Button>(R.id.jogarBtn)

        val escolhaUserCampo = findViewById<EditText>(R.id.escolhaUserCampo)
        val escolhaCPUCampo = findViewById<EditText>(R.id.escolhaCPUCampo)
        val placarUserCampo = findViewById<EditText>(R.id.placarUserCampo)
        val placarCPUCampo = findViewById<EditText>(R.id.placarCPUCampo)

        pedraBtn.setOnClickListener {
            updateSelecao(pedraBtn, "PEDRA", escolhaUserCampo)
            escolhaCPUCampo.setText("")
        }

        papelBtn.setOnClickListener {
            updateSelecao(papelBtn, "PAPEL", escolhaUserCampo)
            escolhaCPUCampo.setText("")
        }

        tesouraBtn.setOnClickListener {
            updateSelecao(tesouraBtn, "TESOURA", escolhaUserCampo)
            escolhaCPUCampo.setText("")
        }

        jogarBtn.setOnClickListener {
            val escolhaUsuario = escolhaUserCampo.text.toString()
            val opcoes = arrayOf("PEDRA", "PAPEL", "TESOURA")
            val escolhaCPU = opcoes[random.nextInt(opcoes.size)]
            escolhaCPUCampo.setText(escolhaCPU)

            val resultado = compararEscolhas(escolhaUsuario, escolhaCPU)

            if (resultado == 1) {
                placarUsuario++
            } else if (resultado == -1) {
                placarCPU++
            }

            placarUserCampo.setText(placarUsuario.toString())
            placarCPUCampo.setText(placarCPU.toString())
        }

    }

    private fun updateSelecao(clickedImageView: ImageView, text: String, escolhaUserCampo: EditText) {
        selecaoImageView?.isSelected = false
        selecaoImageView = clickedImageView
        clickedImageView.isSelected = true
        escolhaUserCampo.setText(text)
    }

    private fun compararEscolhas(escolhaUsuario: String, escolhaCPU: String): Int {
        if (escolhaUsuario == escolhaCPU) {
            return 0 // Empate
        }

        if (
            (escolhaUsuario == "PEDRA" && escolhaCPU == "TESOURA") ||
            (escolhaUsuario == "PAPEL" && escolhaCPU == "PEDRA") ||
            (escolhaUsuario == "TESOURA" && escolhaCPU == "PAPEL")
        ) {
            return 1 // Vitória do usuário
        }

        return -1 // Vitória do CPU
    }
}