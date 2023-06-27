package com.rasgo.combidriver.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.rasgo.combidriver.databinding.ActivityRegisterBinding
import com.rasgo.combidriver.models.Client
import com.rasgo.combidriver.models.Driver
import com.rasgo.combidriver.providers.AuthProvider
import com.rasgo.combidriver.providers.ClientProvider
import com.rasgo.combidriver.providers.DriverProvider

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authProvider= AuthProvider()
    private val driverProvider=DriverProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding.btnLogin.setOnClickListener {goToLogin()}
        binding.btnRegister.setOnClickListener {register()}
    }

    private fun register() {
        val name=binding.textFieldNombre.text.toString()
        val phone=binding.textFieldTelefono.text.toString()
        val email=binding.textFieldEmail.text.toString()
        val password=binding.textFieldPassword.text.toString()
        val confirmPassword=binding.textFieldConfirmPassword.text.toString()

        if (isValidForm(name, phone, email, password, confirmPassword)){
            authProvider.register(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val driver = Driver(
                        id=authProvider.getId(),
                        name=name,
                        phone=phone,
                        email=email
                    )
                    driverProvider.create(driver).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Registro exitoso", Toast.LENGTH_LONG).show()
                            goToMap()
                        }
                        else {
                            Toast.makeText(this@RegisterActivity, "Error al registrar, ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                        }
                    }

                }
                else {
                    Toast.makeText(this@RegisterActivity, "Registro fallido ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goToMap() {
        val i = Intent(this, MapActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    private fun isValidForm(
        name:String,
        phone: String,
        email:String,
        password:String,
        confirmPassword:String
    ):Boolean{
        if(name.isEmpty()){
            Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_SHORT).show()
            return false
        }
        if(phone.isEmpty()){
            Toast.makeText(this, "Ingresa tu número de teléfono", Toast.LENGTH_SHORT).show()
            return false
        }
        if(email.isEmpty()){
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Ingresa una contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(confirmPassword.isEmpty()){
            Toast.makeText(this, "Vuelve a ingresar tu contraseña", Toast.LENGTH_SHORT).show()
            return false
        }
        if(password != confirmPassword){
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6){
            Toast.makeText(this, "La contraseña debe tener 6 caracteres", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }


    private fun goToLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}