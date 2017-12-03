package stijnjj.hueapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

import stijnjj.hueapp.R
import stijnjj.hueapp.Settings

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val s = Settings.createInstance(this)

        val username = findViewById<EditText>(R.id.txtUsername) as EditText
        username.setText(s.username)
        username.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                s.username = username.text.toString()
        }

        val port = findViewById<EditText>(R.id.txtPort) as EditText
        port.setText("" + s.port)
        port.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                s.port = port.text.toString().toInt()
        }

        val location = findViewById<EditText>(R.id.txtAddress) as EditText
        location.setText(s.location)
        location.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                s.location = location.text.toString()
        }

        val connect = findViewById<Button>(R.id.btnConnect) as Button
        connect.setOnClickListener {
            val mActivity = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(mActivity)
        }
    }
}
