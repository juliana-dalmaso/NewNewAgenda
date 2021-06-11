package com.juliana.newagenda

class Trabalho (nomeTrabalho: String,
                celularTrabalho: String,
                val email: String):
    Contato(nomeTrabalho, celularTrabalho) {

    override fun exibir(): String {
        return " $nome - $celular - $email \n"
    }
}