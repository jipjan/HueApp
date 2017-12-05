package stijnjj.hueapp.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import stijnjj.hueapp.*
import stijnjj.hueapp.Json.GroupClasses.Group
import stijnjj.hueapp.Json.LightClasses.Light
import stijnjj.hueapp.Json.LightClasses.LightSettings

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var api = HueApi(this)
        api.getLights({})
        */

        var groups = ArrayList<Group>()
        var lightGroups = ArrayList<LightGroup>()

        var api = HueApi(this)
        api.getGroups {
            // 'it' bevat de lights
            groups = it
            for (group in groups) {
                val lights = ArrayList<LightWithId>()
                for (s in group.lights){
                    api.getInfoLight(s.toInt()){
                        val light = LightWithId(s.toInt(), it)

                        lights.add(light)
                    }
                }
                lightGroups.add(LightGroup(group.name, lights))

            }
            val list = findViewById<ExpandableListView>(R.id.expandedList)
            list.deferNotifyDataSetChanged()
            list.setAdapter(CustomListAdapter(this, lightGroups, resources, api))
        }


//        api.getInfoLight(2, {
//            Log.d("light", it.name)
//        })







//        var settings = LightSettings()
//        settings.isOn = false
//        api.setLightState(2, settings)
    }

}