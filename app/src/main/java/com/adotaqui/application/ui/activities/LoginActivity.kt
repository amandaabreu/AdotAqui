package com.adotaqui.application.ui.activities
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import com.adotaqui.R
import com.adotaqui.application.service.ILoginView
import com.adotaqui.application.service.dao.UsuarioFormViewModel
import com.adotaqui.application.service.login.LoginPresenter
import com.adotaqui.domain.model.Usuario

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity() : AppCompatActivity(), ILoginView{

    private var loginPresenter: LoginPresenter? = null
    lateinit var viewModel : UsuarioFormViewModel
    val EXTRA_USUARIO = "usuario"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(UsuarioFormViewModel::class.java)
        loginPresenter = LoginPresenter(this, this, viewModel)



        sign_in_button.setOnClickListener { loginPresenter!!.onLoginClicked() }
        register_button.setOnClickListener {

            val intent = Intent(this, RegisterUser::class.java)
            startActivity(intent)
            
        }
    }

    override fun getUserEmail(): String {
        return email.text.toString()
    }

    override fun getUserPassword(): String {
       return password.text.toString()
    }

    override fun onError(resId: Int) {
        Toast.makeText(this, getString(resId), LENGTH_LONG).show()
    }

    override fun onSucess() {
        val intent = Intent(applicationContext, Home::class.java)
        startActivity(intent)
    }

    override fun onSucess(usuario: Usuario) {
        if (usuario != null) {
            val intent = Intent(this, Home::class.java)
            intent.putExtra(EXTRA_USUARIO, usuario)
            startActivity(intent)
        }
    }
}
