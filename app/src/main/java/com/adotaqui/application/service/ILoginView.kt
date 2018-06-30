package com.adotaqui.application.service

import com.adotaqui.domain.model.Usuario

interface ILoginView {
    abstract fun getUserEmail(): String
    abstract fun getUserPassword(): String
    abstract fun onError(resId: Int)
    abstract fun onSucess()
    abstract fun onSucess(usuario : Usuario)
}