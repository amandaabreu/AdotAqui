package com.adotaqui.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Usuario : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "id")
    var id: Long = 0L;
    @ColumnInfo (name = "name")
    var name:     String = "";
    @ColumnInfo (name = "email")
    var email:    String = "";
    @ColumnInfo (name = "login")
    var login:    String = "";
    @ColumnInfo(name = "password")
    var password: String = "";
}