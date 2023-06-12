package com.example.appfirestore

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appfirestore.ui.theme.AppFirestoreTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

data class FormData(
    val nome: String,
    val endereco: String,
    val bairro: String,
    val cep: String,
    val cidade: String,
    val estado: String

)


class MainActivity : ComponentActivity() {
    private val firestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                AppFirestore()

            }
        }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppFirestore() {
        val formData = rememberSaveable { mutableStateOf(FormData("", "", "", "", "", "")) }
        val context = LocalContext.current

        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "AppFirestore") })
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = formData.value.nome,
                        onValueChange = { formData.value = formData.value.copy(nome = it) },
                        label = { Text(text = "Nome") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = formData.value.endereco,
                        onValueChange = { formData.value = formData.value.copy(endereco = it) },
                        label = { Text(text = "Endereço") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = formData.value.bairro,
                        onValueChange = { formData.value = formData.value.copy(bairro = it) },
                        label = { Text(text = "Bairro") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = formData.value.cep,
                        onValueChange = { formData.value = formData.value.copy(cep = it) },
                        label = { Text(text = "CEP") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = formData.value.cidade,
                        onValueChange = { formData.value = formData.value.copy(cidade = it) },
                        label = { Text(text = "Cidade") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = formData.value.estado,
                        onValueChange = { formData.value = formData.value.copy(estado = it) },
                        label = { Text(text = "Estado") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    Button(
                        onClick = { saveFormData(context, formData.value) },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text ="Salvar")



                    }
                }
            }
        )
    }

    private fun saveFormData(context: Context, formData: FormData) {
        val firestore = FirebaseFirestore.getInstance()
        val collection = firestore.collection("dados")

        collection.add(formData)
            .addOnSuccessListener {
                Toast.makeText(context, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Falha ao salvar os dados.", Toast.LENGTH_SHORT).show()
            }
    }
}