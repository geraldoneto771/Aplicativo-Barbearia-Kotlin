package com.example.barbeariakotlin.ui.cortes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barbeariakotlin.model.CorteModel
import com.example.barbeariakotlin.repositorie.db.RepositoryDB as CorteDB

class CorteViewModel : ViewModel() {

    // Variaveis repositorio local
    private val _cortes = MutableLiveData<ArrayList<CorteModel>>().apply { value = ArrayList() }
    val cortes: LiveData<ArrayList<CorteModel>> = _cortes

    // Função criar corte
    fun createCorte(corte: CorteModel) {CorteDB().create(corte)}

    // Função pegar as informações do corte no repositorio local
    fun getAllCorte() {CorteDB().getAll(_cortes)}


}