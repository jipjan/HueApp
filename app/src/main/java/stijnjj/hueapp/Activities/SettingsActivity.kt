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


        val username = findViewById(R.id.txtUsername) as EditText
        username.setText(s.username)

        val port = findViewById(R.id.txtPort) as EditText
        port.setText("" + s.port)

        val location = findViewById(R.id.txtLocation) as EditText
        location.setText(s.location)

        val connect = findViewById(R.id.btnConnect) as Button
        connect.setOnClickListener {
            val mActivity = Intent(this@SettingsActivity, MainActivity::class.java)
            startActivity(mActivity)
        }
    }
}
