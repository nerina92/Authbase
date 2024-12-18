package com.cursosant.authbase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

//Ejemplo de prueba inversa
//En esta prueba, debemos evaluar que solo pase la prueba que corresponda en base al email y password que ingresemos

class AuthAlTest{
    private var email: String? = null
    private var password: String? = null

    @Before
    fun setup(){
        email = "nuliana925@gmailcom"
        password = "1234"
    }
    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.USER_EXIST, isAuthenticated)
    }
    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.USER_NOT_EXIST, isAuthenticated)
    }
    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.EMPTY_EMAIL, isAuthenticated)
    }
    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.EMPTY_PASSWORD, isAuthenticated)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.EMPTY_FORM, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.INVALID_EMAIL, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.INVALID_PASSWORD, isAuthenticated)
    }
    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.INVALID_USER, isAuthenticated)
    }

    //Una forma de tratar con exceptions
    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.NULL_EMAIL, isAuthenticated)
    }

    //Otra forma de tratar con exceptions, mas correcta si puede haber varios null por ejemplo
    @Test
    fun login_nullPassword_returnsException() {
        assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD(email,password))
        }
    }
    //Una tercera forma de tratar las exceptions
    @Test
    fun login_nullForm_returnsException() {
        try{
            val isAuthenticated = userAuthenticationTDD(email,password)
            assertEquals(AuthEvent.NULL_FORM, isAuthenticated)
        }catch (e: Exception){
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }
    //de las tres formas tratadas, se recomienda usar siempre una en todos los casos para no tener errores

    //@Ignore("Falta definir un requisito del cliente")//anotacion para que no se haga este test si se ejecuta toda la clase
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD(email,password)
        assertEquals(AuthEvent.ERROR_LENGTH_PASSWORD, isAuthenticated)
    }
}