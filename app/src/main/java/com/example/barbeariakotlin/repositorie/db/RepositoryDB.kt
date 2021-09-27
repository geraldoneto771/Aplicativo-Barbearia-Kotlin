package com.example.barbeariakotlin.repositorie.db

import androidx.lifecycle.MutableLiveData
import com.example.barbeariakotlin.model.CorteModel
import io.realm.Realm
import io.realm.kotlin.where

class RepositoryDB {

    // Criando o produto
    fun create(corte: CorteModel){
        val realm = Realm.getDefaultInstance()

        realm.executeTransaction {
            it.insert(corte)
        }
    }

    fun get(idCorte: String): CorteModel {
        val realm = Realm.getDefaultInstance()
        val project = realm.where<CorteModel>()
            .equalTo("pk", idCorte).findFirst()
        project?.let { aux ->
            return aux
        } ?: run {
            return CorteModel()
        }
    }

    fun getAll(_corte: MutableLiveData<ArrayList<CorteModel>>) {
        val realm = Realm.getDefaultInstance()

        realm.addChangeListener {
            val corte = realm.where<CorteModel>().findAll()
            var list = ArrayList<CorteModel>()

            list.addAll(corte)
            list = ArrayList<CorteModel>()
            list.addAll(corte)
            _corte.postValue(list)

            _corte.value = list
        }
    }
}