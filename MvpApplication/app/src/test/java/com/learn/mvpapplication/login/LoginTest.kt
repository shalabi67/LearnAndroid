package com.learn.mvpapplication.login

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class LoginTest {
    private lateinit var loginPresenter: LoginPresenter
    private lateinit var loginView : LoginView

    private val validUserName = "user"
    private val validPassword = "pass"

    @Before
    fun setup() {
        loginView = mock(LoginView::class.java)
        val loginModel = mock(LoginModel::class.java)

        loginPresenter = LoginPresenter(loginModel)
        loginPresenter.setView(loginView)

        val validUser = User(validUserName, validPassword)
        Mockito.`when`(loginModel.getLoggedUser()).thenReturn(validUser)


    }

    @Test
    fun testValidUserLogin() {
        val validUser = User(validUserName, validPassword)
        initializeLoginViewUser(loginView, validUser)
        Mockito.`when`(loginView.close()).thenAnswer {  }
        Mockito.`when`(loginView.showError(Mockito.anyString()))
                .thenAnswer { Assert.fail("showError should not be called") }

        Assert.assertTrue(loginPresenter.login())
    }

    @Test
    fun testInvalidUserLogin() {
        Mockito.`when`(loginView.close())
                .thenAnswer { Assert.fail("showError should not be called")  }
        Mockito.`when`(loginView.showError(Mockito.anyString())).thenAnswer { }

        for(invalidUser in invalidUsers) {
            initializeLoginViewUser(loginView, invalidUser)
            Assert.assertFalse(loginPresenter.login())
        }
    }

    private fun initializeLoginViewUser(loginView : LoginView, user: User) {
        Mockito.`when`(loginView.getUserName()).thenReturn(user.userName)
        Mockito.`when`(loginView.getPassword()).thenReturn(user.password)
    }

    val invalidUsers =arrayOf(
            User("", ""),
            User("1", "2"),
            User(validUserName, validUserName),
            User(validPassword, validPassword),
            User(validUserName,validPassword + 1),
            User(validUserName +1, validPassword)
    )
}