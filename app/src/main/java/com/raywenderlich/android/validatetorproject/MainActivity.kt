package com.raywenderlich.android.validatetorproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.raywenderlich.android.validatetor.ValidateTor
import com.raywenderlich.android.validatetorproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var validateTor: ValidateTor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateTor = ValidateTor()
        setUpUiWidgets()
    }

    private fun setUpUiWidgets() {
        binding.btnValidate.setOnClickListener {
            validateEmailField(binding.edtEmail)
            validatePasswordField(binding.edtPassword)
            validateCreditCardField(binding.edtCreditcard)
        }
    }

    private fun validateCreditCardField(editText: EditText) {
        val str = editText.text.toString()
        if (validateTor.isEmpty(str)) {
            editText.error = "Field is empty!"
        }

        if (!validateTor.validateCreditCard(str)) {
            editText.error = "Invalid Credit Card number!"
        } else {
            Toast.makeText(this, "Valid Credit Card Number!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validatePasswordField(editText: EditText) {
        val str = editText.text.toString()
        if (validateTor.isEmpty(str)) {
            editText.error = "Field is empty!"
        }

        if (validateTor.isAtleastLength(str, 8)
            && validateTor.hasAtleastOneDigit(str)
            && validateTor.hasAtleastOneUppercaseCharacter(str)
            && validateTor.hasAtleastOneSpecialCharacter(str)) {
            Toast.makeText(this, "Valid Password!", Toast.LENGTH_SHORT).show()
        } else {
            editText.error = "Password needs to be a minimum length of 8 characters and should have at least 1 digit, 1 upppercase letter and 1 special character "
        }

    }

    private fun validateEmailField(editText: EditText) {
        val str = editText.text.toString()
        if (validateTor.isEmpty(str)) {
            editText.error = "Field is empty!"
        }

        if (!validateTor.isEmail(str)) {
            editText.error = "Invalid Email"
        } else {
            Toast.makeText(this, "Valid Email!", Toast.LENGTH_SHORT).show()
        }
    }
}
