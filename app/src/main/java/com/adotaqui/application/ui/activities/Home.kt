package com.adotaqui.application.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.adotaqui.R
import com.adotaqui.application.service.dao.UsuarioFormViewModel
import com.adotaqui.domain.model.Usuario
import kotlinx.android.synthetic.main.home.*

class Home : AppCompatActivity() {

    lateinit var viewModel : UsuarioFormViewModel
    lateinit var usuario : Usuario
    val EXTRA_USUARIO = "usuario"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)


        try{
            usuario = intent.getSerializableExtra(EXTRA_USUARIO) as? Usuario ?: Usuario()
            setTitle(usuario.name.toString())

            button_call_observable.setOnClickListener {
                val intent = Intent(this, RegisterUser::class.java)
                intent.putExtra(EXTRA_USUARIO, usuario)
                startActivity(intent)
            }
        }catch (error : Exception){
            var status = error.toString();
        }
    }

    override fun onResume() {
        super.onResume()

        try{
            val model = ViewModelProviders.of(this).get(UsuarioFormViewModel::class.java)
            model.getUserWithLoginLiveData(usuario.email, usuario.password).observe(this, Observer { usuario ->
                if (usuario != null) {
                    setTitle(usuario.name.toString())
                }else{
                    setTitle("deletado")
                }
            })
            /**
            val model = ViewModelProviders.of(this).get(UsuarioFormViewModel::class.java)
            model.getUsersLiveData().observe(this, Observer { people ->
                if (people != null) {

                }
            })
            */
        }catch (error : Exception){
            var status = error.toString();
        }
    }

}