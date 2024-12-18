package com.cursosant.authbase

import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.Test

class AuthTest {
    //en esta clase se probará el login

    //Forma de nombrar los test: acción_escenario_esperado
    @Test
    fun loguin_complete_returnsTrue(){
        val isAuthenticated = userAuthentication("nuliana925@gmail.com", "1234")
        assertTrue(isAuthenticated)
    }

    @Test
    fun loguin_complete_returnsFalse(){
        val isAuthenticated = userAuthentication("uliana925@gmail.com", "1234")
        assertFalse(isAuthenticated)
    }

    @Test
    fun loguin_emptyEmail_returnsFalse(){
        val isAuthenticated = userAuthentication("", "1234")
        assertFalse(isAuthenticated)
    }

    /* - TDD -
    @Test
    fun loguin_nullEmail_returnsFalse(){
        val isAuthenticated = userAuthenticationTDD(null, "1234")
        assertFalse(isAuthenticated)
    }

    @Test
    fun loguin_nullPassword_returnsFalse(){
        val isAuthenticated = userAuthenticationTDD("uliana925@gmail.com", null)
        assertFalse(isAuthenticated)
    }*/
}
/*TDD: TEST DRIVEN DEVELOPMENT - DESARROLLO BASEADO EN PRUEBAS

      WRITE FAILING TEST
   /\                      \
   /                       \/
REFACTOR  <-----   MAKE THE TEST PASS*/