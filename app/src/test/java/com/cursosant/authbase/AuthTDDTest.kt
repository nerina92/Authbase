package com.cursosant.authbase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test

class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "1234")
        assertEquals(AuthEvent.USER_EXIST, isAuthenticated)
    }
    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana92@gmail.com", "1234")
        assertEquals(AuthEvent.USER_NOT_EXIST, isAuthenticated)
    }
    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "1234")
        assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }
    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "")
        assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "")
        assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925", "1234")
        assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "123a")
        assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail", "123a")
        assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

    //Una forma de tratar con exceptions
    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val isAuthenticated = userAuthenticationTDD(null, "123a")
        assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }

    //Otra forma de tratar con exceptions, mas correcta si puede haber varios null por ejemplo
    @Test
    fun login_nullPassword_returnsException() {
        assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("algo@algo.com", null))
        }
    }
    //Una tercera forma de tratar las exceptions
    @Test
    fun login_nullForm_returnsException() {
        try{
            val isAuthenticated = userAuthenticationTDD(null, null)
            assertEquals(AuthEvent.NULL_FORM, isAuthenticated)
        }catch (e: Exception){
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM , it.authEvent)
            }
        }
    }
    @Ignore("Falta definir un requisito del cliente")//anotacion para que no se haga este test si se ejecuta toda la clase
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "12")
        assertEquals(AuthEvent.ERROR_LENGTH_PASSWORD, isAuthenticated)
    }

}