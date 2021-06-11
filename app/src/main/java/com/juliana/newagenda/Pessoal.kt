package com.juliana.newagenda

class Pessoal (nomePessoal: String,
               celularPessoal: String,
               val  referencia: String):
    Contato (nomePessoal, celularPessoal) {

    override fun exibir(): String {
        return " $nome - $celular - $referencia \n"
    }
}