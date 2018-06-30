package com.adotaqui.application.service.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.adotaqui.R
import com.adotaqui.application.service.ILoginView
import com.adotaqui.application.service.dao.UsuarioFormViewModel
import com.adotaqui.application.ui.activities.RegisterUser
import com.adotaqui.domain.model.Usuario
import org.json.JSONObject

class LoginPresenter(){
    internal lateinit var context: Context
    internal lateinit var view: ILoginView

    lateinit var usuario : Usuario
    lateinit var viewModel : UsuarioFormViewModel


    constructor(context : Context, view : ILoginView, viewModel : UsuarioFormViewModel) : this() {
        this.context = context
        this.view = view

        this.viewModel = viewModel
        this.usuario = Usuario()
    }


    fun onLoginClicked() {
        try {
            val email = view.getUserEmail()
            if (email.isEmpty()) {
                view.onError(R.string.strLyLoginUserEmail_error)
                return
            }
            val password = view.getUserPassword()
            if (password.isEmpty()) {
                view.onError(R.string.strLyLoginPassword_error)
                return
            }

            /**
            val loginSucceeded = viewModel.checkUserWithLogin(email.toString(), password.toString());
            if (loginSucceeded!!) {
            }
            */

            val usuario = viewModel.getUserWithLogin(email.toString(), password.toString());
            if (usuario != null) {
                view.onSucess(usuario)
            } else {
                view.onError(R.string.strLyLoginOnError)
            }
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}