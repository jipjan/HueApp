package stijnjj.hueapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import stijnjj.hueapp.CustomListAdapter
import stijnjj.hueapp.HueApi
import stijnjj.hueapp.Json.GroupClasses.Group
import stijnjj.hueapp.Json.LightClasses.Light
import stijnjj.hueapp.Json.LightClasses.LightSettings

import stijnjj.hueapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var api = HueApi(this)
        api.getLights({})
        */

        var groups = ArrayList<Group>()
        var lights = ArrayList<Light>()

        var api = HueApi(this)
        api.getLights {
            // 'it' bevat de lights
            for (light in it) {
                println(light.name)
            }
            lights = it

            api.getGroups {
                // 'it' bevat de groups
                for (g in it){
                    println(g.name)
                }
                groups = it

                val list = findViewById<ExpandableListView>(R.id.expandedList)
                //list.deferNotifyDataSetChanged()
                list.setAdapter(CustomListAdapter(this, groups, lights, resources, api))
            }
        }


//        api.getInfoLight(2, {
//            Log.d("light", it.name)
//        })







//        var settings = LightSettings()
//        settings.isOn = false
//        api.setLightState(2, settings)
    }

}