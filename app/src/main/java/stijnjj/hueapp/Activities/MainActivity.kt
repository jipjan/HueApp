package stijnjj.hueapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import stijnjj.hueapp.HueApi

import stijnjj.hueapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var api = HueApi(this)
        api.getLights({})
        */

        var api = HueApi(this)
        api.getLights {
            // 'it' bevat de lights
            for (light in it) {
                Log.d("lights", light.name)
            }
        }
        api.getGroups {
            // 'it' bevat de lights
            for (group in it) {
                Log.d("groups", group.name)
            }
        }
    }

}