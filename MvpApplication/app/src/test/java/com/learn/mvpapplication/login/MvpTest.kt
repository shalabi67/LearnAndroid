package com.learn.mvpapplication.login

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import com.learn.mvpapplication.R
import com.learn.mvpapplication.mocks.EditTextMock
import kotlinx.android.synthetic.main.activity_login.view.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

class MvpTest {
    private lateinit var loginView : LoginView
    private lateinit var loginModel: LoginModel
    private lateinit var loginPresenter: LoginPresenter

    private lateinit var loginActivity : Activity
    private val userNameEditText: EditTextMock = EditTextMock()
    private val passwordEditText: EditTextMock = EditTextMock()
    private lateinit var loginButton: Button

    private val validUserName = "user"
    private val validPassword = "password"

    @Before
    fun setup() {
        loginActivity = mock(Activity::class.java)

        setupActivity(loginActivity)

        setupMvp()
    }

    private fun setupMvp() {
        loginModel = mock(LoginModel::class.java)
        loginPresenter = LoginPresenter(loginModel)
        loginView = LoginView(loginPresenter)

        Mockito.`when`(loginModel.getLoggedUser()).thenReturn(User(validUserName, validPassword))
        loginView.setActivity(loginActivity)
    }

    private fun setupActivity(loginActivity: Activity) {
        loginButton = mock(Button::class.java)
        Mockito.`when`(loginActivity.findViewById<EditText>(R.id.userNameEditTextId))
                .thenReturn(userNameEditText.getEditText())
        Mockito.`when`(loginActivity.findViewById<EditText>(R.id.passwordEditTextId))
                .thenReturn(passwordEditText.getEditText())
        Mockito.`when`(loginActivity.findViewById<Button>(R.id.login_button_id))
                .thenReturn(loginButton)
        Mockito.`when`(loginActivity.finish()).thenAnswer {  }

        userNameEditText.setText(validUserName)
        passwordEditText.setText(validPassword)
    }

    @Test
    fun testLogin() {
        Assert.assertTrue(loginPresenter.login())
        Mockito.verify(loginActivity, times(1)).finish()
    }
}