package com.juliana.newagenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    var nomeDigitado: EditText? = null
    var celularDigitado: EditText? = null
    lateinit var pesquisarDigitada: EditText
    lateinit var referenciaDigitada: EditText

    lateinit var salvar: Button
    lateinit var lupa: Button
    lateinit var exibir: Button

    lateinit var lista: TextView

    lateinit var rdgSetor: RadioGroup
    lateinit var pessoal: RadioButton

    lateinit var setorSelecionado: Setor

    private var nomesCadastrados: MutableList<Contato> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nomeDigitado = findViewById(R.id.edtNome)
        celularDigitado = findViewById(R.id.edtCelular)
        rdgSetor = findViewById(R.id.rdgSetor)
        pessoal = findViewById(R.id.rdPessoal)
        referenciaDigitada = findViewById(R.id.edtReferencia)
        pesquisarDigitada = findViewById(R.id.edtPesquisar)
        lupa = findViewById(R.id.btnPesquisar)
        salvar = findViewById(R.id.btnSalvar)
        lista = findViewById(R.id.txtResultado)
        exibir = findViewById(R.id.btnExibir)


        salvar.setOnClickListener {
            exibir.visibility = View.INVISIBLE
            var exibirContatos = ""
            val nome = nomeDigitado?.text.toString()
                    if (nome.isEmpty())  nomeDigitado?.error= getString(R.string.semInformacao)
            val celular = celularDigitado?.text.toString()
                    if (celular.isEmpty())  celularDigitado?.error=getString(R.string.semInformacao)

                    if(!(nome.isEmpty() || (celular.isEmpty()))){
                        if (pessoal.isChecked) {
                            val referencia = referenciaDigitada.text.toString()
                            var cttPessoal = Pessoal(nome, celular, referencia)
                            nomesCadastrados.add(cttPessoal)

                        } else {
                            val email = referenciaDigitada.text.toString()
                            var cttTrabalho = Trabalho(nome, celular, email)
                            nomesCadastrados.add(cttTrabalho)

                        }

                        referenciaDigitada.setText("")
                        nomeDigitado?.setText("")
                        celularDigitado?.setText("")

                        nomesCadastrados.sortBy { it.nome }

                        for (N in nomesCadastrados) {
                            exibirContatos += N.exibir()
                        }
                        lista.text = exibirContatos
                    }else{
                        Toast.makeText(this,
                            getString(R.string.informacaoo),
                            Toast.LENGTH_SHORT).show()
                    }



        }



        lupa.setOnClickListener{
            lista.text = ""
            var pesquisar = pesquisarDigitada.text.toString()
            var resultado = nomesCadastrados.find{X -> X.nome == pesquisar}

            if (resultado != null) {
                lista.text = resultado.exibir()

            } else {
                Toast.makeText(this,
                    getString(R.string.semPesquisa),
                    Toast.LENGTH_SHORT).show()
            }
            exibir.visibility = View.VISIBLE
            pesquisarDigitada.setText("")

        }


        exibir.setOnClickListener{

            var exibirContatos = ""

            for (N in nomesCadastrados) {
                exibirContatos += N.exibir()
            }
            lista.text = exibirContatos
            exibir.visibility = View.INVISIBLE

        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val foiChecado = view.isChecked

            when (view.id) {

                R.id.rdPessoal ->
                    if (foiChecado) {
                        setorSelecionado = Setor.PESSOAL
                        referenciaDigitada.setHint(getString(R.string.referencia))
                        referenciaDigitada.setInputType(InputType.TYPE_CLASS_TEXT)
                    }

                R.id.rdTrabalho ->
                    if (foiChecado) {
                        setorSelecionado = Setor.TRABALHO
                        referenciaDigitada.setHint(getString(R.string.email))
                        referenciaDigitada.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                    }
            }

        }
    }
}