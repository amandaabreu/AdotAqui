package com.adotaqui.application.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adotaqui.R
import com.adotaqui.application.service.dao.UsuarioFormViewModel
import com.adotaqui.domain.model.Usuario
import kotlinx.android.synthetic.main.registeruser.*

class RegisterUser : AppCompatActivity() {


    lateinit var usuario : Usuario
    lateinit var viewModel : UsuarioFormViewModel
    val EXTRA_USUARIO = "usuario"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registeruser)

        viewModel = ViewModelProviders.of(this).get(UsuarioFormViewModel::class.java)

        usuario = intent.getSerializableExtra(EXTRA_USUARIO) as? Usuario ?: Usuario()

        edtName.setText(usuario.name)
        edtEmail.setText(usuario.email)
        edtLogin.setText(usuario.login)
        edtPassword.setText(usuario.password)


        btnRemove.visibility = if (usuario.id == 0L) View.GONE else View.VISIBLE

        btnCancel.setOnClickListener { finish()}
        btnSave.setOnClickListener { savePerson() }
        btnRemove.setOnClickListener { removePerson() }
    }

    private fun removePerson() {
        viewModel.deletePerson(usuario)
        finish()
    }

    private fun savePerson() {
        usuario.name = edtName.text.toString()
        usuario.email = edtEmail.text.toString()
        usuario.login = edtLogin.text.toString()
        usuario.password = edtPassword.text.toString()

        viewModel.saveUsuario(usuario)
        finish()
    }
}