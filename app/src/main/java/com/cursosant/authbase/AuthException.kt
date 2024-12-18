package com.cursosant.authbase

class AuthException (val authEvent: AuthEvent, msg: String? = null) : Exception(msg) {
}