package com.mc.qr.retrofit.response

data class ResponseLogin(
    val id: Long,
    val mensaje: String,
    val user: User,
    val nombre: String,
    val token: String,
)
data class User(
    val password: Any?,
    val username: String,
    val authorities: List<Any?>,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
)
