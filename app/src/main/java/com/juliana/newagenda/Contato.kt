package com.juliana.newagenda

open class Contato (open val nome: String,
                    open var celular: String) {

    open fun exibir(): String {
        return " $nome - $celular \n"
    }
}