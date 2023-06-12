package com.example.appfirestore.repositorio

import com.example.appfirestore.datasource.DataSource

class DataRepositorio(private val dataSource: DataSource) {

    fun salvarDados(nome: String, endereco: String, bairro: String, cep: String, cidade: String, estado: String){
        dataSource.salvarDados(nome, endereco, bairro, cep, cidade, estado)
    }
}