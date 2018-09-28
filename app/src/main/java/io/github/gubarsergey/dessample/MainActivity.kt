package io.github.gubarsergey.dessample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.github.gubarsergey.dessample.encryption.EXPECTED_KEY_LENGTH
import io.github.gubarsergey.dessample.encryption.Encryptor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {
        encrypt_button.setOnClickListener {
            val key = key_edit_text.text.toString()
            val textToEncrypt = encrypt_edit_text.text.toString()
            validateKey(key) {
                val encryptedMessage = Encryptor.encrypt(textToEncrypt, key)
                encrypted_result_text_view.text = encryptedMessage
            }
        }

        decrypt_button.setOnClickListener {
            val encryptedMessage = encrypted_result_text_view.text.toString()
            val key = key_edit_text.text.toString()
            validateKey(key) {
                val decryptedMessage = Encryptor.decrypt(encryptedMessage, key)
                decrypted_text_view.text = decryptedMessage
            }
        }
    }

    private fun validateKey(key: String, doOnSuccess: () -> Unit) {
        if (key.length == EXPECTED_KEY_LENGTH) {
            doOnSuccess()
        } else {
            Toast.makeText(this, "Key length must be 7 symbols (56 bit)!", Toast.LENGTH_SHORT).show()
        }
    }
}
