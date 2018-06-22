package com.adotaqui.application.service.dao

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.adotaqui.domain.dao.AppDatabase
import com.adotaqui.domain.dao.UsuarioDao
import com.adotaqui.domain.model.Usuario

class UsuarioFormViewModel(app : Application) : AndroidViewModel(app) {
    private var db: UsuarioDao? = null
    var liveUsuario: LiveData<List<Usuario>>? = null

    var liveUsuarioLogin: LiveData<Usuario>? = null

    private fun usuarioDao() : UsuarioDao? {
        if (db == null) {
            db = AppDatabase.getDatabase(getApplication())?.usuarioDao()
        }
        return db
    }

    fun saveUsuario(usuario : Usuario) {

        if (usuario.id == 0L) {
            usuarioDao()?.insertUsuario(usuario)

        } else {
            usuarioDao()?.updateUsuario(usuario)
        }
    }
    fun deletePerson(usuario: Usuario) {
        usuarioDao()?.deleteUsuario(usuario)
    }

    fun checkUserWithLogin(email: String, password: String): Boolean? {
        return usuarioDao()?.checkUserByLogin(email, password)
    }

    fun getUserWithLogin(email: String, password: String): Usuario? {
        return usuarioDao()?.loadUserByLogin(email, password)
    }

    fun getUserWithLoginLiveData(email: String, password: String): LiveData<Usuario> {
        if (liveUsuarioLogin == null) {
            liveUsuarioLogin = usuarioDao()?.loadUserByLoginLiveData(email, password)
        }
        return liveUsuarioLogin as LiveData<Usuario>
    }

    fun getUsersLiveData(): LiveData<List<Usuario>> {
        if (liveUsuario == null) {
            liveUsuario = usuarioDao()?.listAll()
        }
        return liveUsuario as LiveData<List<Usuario>>
    }


}