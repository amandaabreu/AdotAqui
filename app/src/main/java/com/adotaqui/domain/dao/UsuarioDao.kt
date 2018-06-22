package com.adotaqui.domain.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.adotaqui.domain.model.Usuario

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsuario(user: Usuario)

    @Update
    fun updateUsuario(user: Usuario)

    @Delete
    fun deleteUsuario(vararg user: Usuario)

    @Query("SELECT * from Usuario where email = :email AND password = :password")
    fun checkUserByLogin(email: String, password: String): Boolean

    @Query("SELECT * from Usuario where email = :email AND password = :password")
    fun loadUserByLogin(email: String, password: String): Usuario

    @Query("SELECT * from Usuario where email = :email AND password = :password")
    fun loadUserByLoginLiveData(email: String, password: String):  LiveData<Usuario>

    @Query("SELECT * FROM Usuario ORDER BY name")
    fun listAll(): LiveData<List<Usuario>>

    /**
    @Query("SELECT * from Usuario where id = :id LIMIT 1")
    fun loadUserById(id: Int): Flowable<Usuario>

    @Query("SELECT * from Usuario where login = :login AND password = :password")
    fun loadUserByLogin(login: String, password: String): Flowable<Usuario>
     */
}