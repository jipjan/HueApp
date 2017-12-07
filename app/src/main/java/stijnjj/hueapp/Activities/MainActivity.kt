package stijnjj.hueapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import stijnjj.hueapp.*
import stijnjj.hueapp.Json.GroupClasses.Action
import stijnjj.hueapp.Json.GroupClasses.AdvancedAction
import stijnjj.hueapp.Json.GroupClasses.Group
import stijnjj.hueapp.Json.GroupClasses.SingleGroup
import stijnjj.hueapp.Json.LightClasses.Light

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var api = HueApi(this)
        api.getLights({})
        */

        var groups: ArrayList<Group>

        val lightGroups = ArrayList<ExpandableGroup>()

        val api = HueApi(this)
        api.getGroups {
            groups = it

            for (i in 0..groups.size) {
                api.getGroupInfo(i) {
                    val group = GroupWithId(i, it)
                    val lights = ArrayList<LightWithId>()
                    for (s in group.group.lights) {
                        api.getInfoLight(s.toInt()) {
                            val light = LightWithId(s.toInt(), it)
                            println(it.name)
                            lights.add(light)
                        }
                    }
                    waitTillListIsFilled(groups.size, lightGroups, HueGroup(group, lights), api)
                }
            }
        }
    }
    fun waitTillListIsFilled(numElements: Int, list: ArrayList<ExpandableGroup>, newElement: ExpandableGroup, api: HueApi){
        list.add(newElement)
        if (list.size == numElements){
            // start adding all lights
            var singlegrp = SingleGroup()
            singlegrp.name = "All lights"
            singlegrp.action = AdvancedAction()
            singlegrp.action.hue = 0
            singlegrp.action.bri = 0
            singlegrp.action.sat = 0
            singlegrp.action.on = true
            val grp = GroupWithId(0, singlegrp)
            val newlights = ArrayList<LightWithId>()
            api.getLights {
                for (i in 0..it.size-1)
                    newlights.add(LightWithId(i+1, it[i]))

                list.add(HueGroup(grp, newlights))

                // End of system to add all lights
                val listView = findViewById<ExpandableListView>(R.id.expandedList)
                listView.setAdapter(CustomListAdapter(this, list, resources, api))
            }

        }
    }
}