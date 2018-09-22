package com.learn.mvpapplication.mocks

import android.text.Editable
import android.widget.EditText
import org.mockito.Mockito
import org.mockito.Mockito.mock

class EditTextMock {
    private val editText : EditText = mock(EditText::class.java)
    private val editable : Editable = mock(Editable::class.java)

    fun setText(text : String) {
        Mockito.`when`(editText.text).thenReturn(editable)
        Mockito.`when`(editable.toString()).thenReturn(text)
    }

    fun getEditText() : EditText {
        return editText
    }

}