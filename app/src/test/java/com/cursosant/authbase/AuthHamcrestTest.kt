package com.cursosant.authbase


import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.both
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.endsWith
import org.hamcrest.Matchers.everyItem
import org.hamcrest.Matchers.hasItemInArray
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.Ignore
import org.junit.Test

//HAMCREST es un marco de escritura que funciona con base a "matchers", nos ayuda a escribir
//comparaciones de objetos o valores, de una forma muy descriptiva.

class AuthHamcrestTest {
    //Forma de nombrar los métodos de test:
    //givenn-when-then (dado-cuando-entonces))
    @Test
    fun loginUser_correctData_returnsSuccessEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "1234")
        assertThat(AuthEvent.USER_EXIST, `is` (isAuthenticated))
    }
    @Test
    fun loginUser_wrongData_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana92@gmail.com", "1234")
        assertThat(AuthEvent.USER_NOT_EXIST, `is` (isAuthenticated))
    }
    @Test
    fun loginUser_emptyEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is` (isAuthenticated))
    }
    @Test
    fun loginUser_emptyPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is` (isAuthenticated))
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is` (isAuthenticated))
    }
    @Test
    fun loginUser_invalidEmail_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925", "1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is` (isAuthenticated))
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "123a")
        assertThat(AuthEvent.INVALID_PASSWORD, `is` (isAuthenticated))
    }
    @Test
    fun loginUser_invalidFormatUser_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail", "123a")
        assertThat(AuthEvent.INVALID_USER, `is` (isAuthenticated))
    }

    //Una forma de tratar con exceptions
    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException(){
        val isAuthenticated = userAuthenticationTDD(null, "123a")
        assertThat(AuthEvent.NULL_EMAIL, `is` (isAuthenticated))
    }

    //Otra forma de tratar con exceptions, mas correcta si puede haber varios null por ejemplo
    @Test
    fun loginUser_nullPassword_returnsException() {
        /*assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("algo@algo.com", null))
        }*/
        try{
            val isAuthenticated = userAuthenticationTDD(null, null)
            assertThat(AuthEvent.NULL_FORM, `is` (isAuthenticated))
        }catch (e: Exception){
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM, `is` (it.authEvent))
            }
        }
    }
    //Una tercera forma de tratar las exceptions
    @Test
    fun loginUser_nullForm_returnsException() {
        try{
            val isAuthenticated = userAuthenticationTDD(null, null)
            assertThat(AuthEvent.NULL_FORM, `is` (isAuthenticated))
        }catch (e: Exception){
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM, `is` (it.authEvent))
            }
        }
    }
    //@Ignore("Falta definir un requisito del cliente")//anotacion para que no se haga este test si se ejecuta toda la clase
    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent(){
        val isAuthenticated = userAuthenticationTDD("nuliana925@gmail.com", "12")
        assertThat(AuthEvent.ERROR_LENGTH_PASSWORD, `is` (isAuthenticated))
    }

    @Test
    fun checkNames_differentUsers_match(){
        //assertThat("Maria", containsString("a"))
        assertThat("Maria", both(containsString("a")).and(containsString("i")))
    }
    @Test
    fun checkData_emailPassword_noMatch(){
        val email="123@gmail.com"
        val pass="1234"
        assertThat(email, not(`is` (pass)))
    }
    @Test
    fun checkExist_newEmail_returnString(){
        val oldEmail="123@gmail.com"
        val newEmail="456@gmail.com"
        val emails= arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }
    @Test
    fun checkDomain_arrayEmails_returnString(){
        val nextEmail="678@gmail.com"
        val oldEmail="123@gmail.com"
        val newEmail="456@lalala.com"
        val emails= arrayListOf(oldEmail, newEmail, nextEmail)
        val newEmails= emails.filter { it.contains("@gmail.com") }
        //assertThat(emails, everyItem(endsWith("@gmail.com"))) //en este caso falla porque hay un imail @lalala.com
        assertThat(newEmails, everyItem(endsWith("@gmail.com")))
    }
}

//Significado y uso de TDD
//Formas de tratar las excepciones en las pruebas uniotarias
//Hamcrest