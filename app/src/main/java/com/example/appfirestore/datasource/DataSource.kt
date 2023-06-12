package com.example.appfirestore.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource {
    private val db = FirebaseFirestore.getInstance()
    fun salvarDados(nome: String, endereco: String, bairro: String, cep: String, cidade: String, estado: String){

        val dadoMap = hashMapOf(
            "dado" to nome,
            "dado" to endereco,
            "dado" to bairro,
            "dado" to cep,
            "dado" to cidade,
            "dado" to estado
        )
        db.collection("dados").document(nome).set(dadoMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
}