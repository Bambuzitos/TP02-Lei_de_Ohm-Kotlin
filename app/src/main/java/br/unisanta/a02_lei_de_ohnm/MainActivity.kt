package br.unisanta.a02_lei_de_ohnm

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var edtTensao: EditText
    private lateinit var edtResistencia: EditText
    private lateinit var edtCorrente: EditText
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private val calculadora = OhmCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtTensao = findViewById(R.id.edtTensao)
        edtResistencia = findViewById(R.id.edtResistencia)
        edtCorrente = findViewById(R.id.edtCorrente)
        txtResultado = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener { calcular() }
    }

    private fun calcular() {
        val tensao = edtTensao.text.toString().toDoubleOrNull()
        val resistencia = edtResistencia.text.toString().toDoubleOrNull()
        val corrente = edtCorrente.text.toString().toDoubleOrNull()

        when {
            tensao != null && resistencia != null && corrente == null -> {
                val resultado = calculadora.calcularCorrente(tensao, resistencia)
                txtResultado.text = "Corrente (I) = %.2f A".format(resultado)
            }
            tensao != null && corrente != null && resistencia == null -> {
                val resultado = calculadora.calcularResistencia(tensao, corrente)
                txtResultado.text = "Resistência (R) = %.2f Ω".format(resultado)
            }
            resistencia != null && corrente != null && tensao == null -> {
                val resultado = calculadora.calcularTensao(resistencia, corrente)
                txtResultado.text = "Tensão (V) = %.2f V".format(resultado)
            }
            else -> {
                Toast.makeText(this, "Informe apenas 2 valores para calcular o terceiro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
