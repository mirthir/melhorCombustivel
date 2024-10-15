package com.mirthir.melhorcombustivel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var campoPrecoGasolina: EditText
    lateinit var campoPrecoAlcool: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        defineCampos()
        defineBotao()
    }

    private fun defineCampos() {
        campoPrecoGasolina = findViewById(R.id.campoValorGasolina)
        campoPrecoAlcool = findViewById(R.id.campoValorAlcool)
    }

    private fun defineBotao() {
        val botaoCalcular = findViewById<Button>(R.id.botaoCalcular)
        botaoCalcular.setOnClickListener {
            val precoAlcool = campoPrecoAlcool.text.toString().toDouble()
            val precoGasolina = campoPrecoGasolina.text.toString().toDouble()
            val combustivel = calcularMelhorCombustivel(precoGasolina, precoAlcool)
            mostraResultado(combustivel)
        }
    }

    private fun mostraResultado(resultado: Combustivel) {
        val resultadoTextView = findViewById<TextView>(R.id.combustivelMaisVantajoso)
        resultadoTextView.text = "O Combustível indicado é ${resultado.toString()}"
    }

    private fun calcularMelhorCombustivel(
        precoGasolina: Double,
        precoAlcool: Double) : Combustivel {
        val fatorEscolha = 0.7
        val proporcao = precoAlcool / precoGasolina

        return if (proporcao < fatorEscolha) {
            Combustivel.ALCOOL
        } else {
            Combustivel.GASOLINA
        }
    }

    enum class Combustivel(private val nome: String) {
        GASOLINA("Gasolina"),
        ALCOOL("Álcool");

        override fun toString(): String {
            return nome
        }
    }
}
