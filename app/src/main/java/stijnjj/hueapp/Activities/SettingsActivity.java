package stijnjj.hueapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stijnjj.hueapp.R;
import stijnjj.hueapp.Settings;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Settings s = Settings.createInstance(this);


        EditText username = (EditText) findViewById(R.id.txtUsername);
        username.setText(s.getUsername());

        EditText port = (EditText) findViewById(R.id.txtPort);
        port.setText(""+s.getPort());

        EditText location = (EditText) findViewById(R.id.txtLocation);
        location.setText(s.getLocation());

        Button connect = (Button) findViewById(R.id.btnConnect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mActivity = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(mActivity);
            }
        });
    }
}
