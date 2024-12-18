package com.cursosant.authbase

/****
 * Project: Auth
 * From: com.cursosant.authbase
 * Created by Alain Nicolás Tello on 14/12/21 at 12:05
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/

fun userAuthentication(email: String, password: String): Boolean {
    if (email == "nuliana925@gmail.com" && password == "1234"){
        return true
    }
    return false
}

fun userAuthenticationTDD(email: String?, password: String?): AuthEvent {
    if(email==null && password==null) throw AuthException(AuthEvent.NULL_FORM)
    if(email==null) throw AuthException(AuthEvent.NULL_EMAIL)
    if(password==null) throw AuthException(AuthEvent.NULL_PASSWORD)
    if(email.isEmpty() && password.isEmpty()) return AuthEvent.EMPTY_FORM
    if(email.isEmpty()) return AuthEvent.EMPTY_EMAIL
    if(password.isEmpty() ) return AuthEvent.EMPTY_PASSWORD
    return if(!isEmailValid(email) &&  !isValidPassword(password) )
         AuthEvent.INVALID_USER
    else if(!isEmailValid(email))
        AuthEvent.INVALID_EMAIL
    else if( !isValidPassword(password))
        AuthEvent.INVALID_PASSWORD
    else{
        if(!isValidLengthPassword(password)) return AuthEvent.ERROR_LENGTH_PASSWORD
        else {
            return if (email == "nuliana925@gmail.com" && password == "1234")
                AuthEvent.USER_EXIST
            else AuthEvent.USER_NOT_EXIST
        }
    }
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email);
}
//para el ejemplo suponemos que soloson validos los password que contienen solo numeros
fun isValidPassword(password: String): Boolean {
    return password.toIntOrNull()!=null
}
fun isValidLengthPassword(password: String): Boolean {
    return password.length == 4
}