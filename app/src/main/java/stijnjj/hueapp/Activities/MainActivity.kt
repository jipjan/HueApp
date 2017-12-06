package stijnjj.hueapp.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import stijnjj.hueapp.*
import stijnjj.hueapp.Json.GroupClasses.Group

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        var api = HueApi(this)
        api.getLights({})
        */

        var groups: ArrayList<Group>

        val lightGroups = ArrayList<Pair<GroupWithId, ArrayList<LightWithId>>>()

        val api = HueApi(this)
        api.getGroups {
            groups = it

            for (i in 1..groups.size) {
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
                    waitTillListIsFilled(groups.size, lightGroups, Pair(group, lights), api)
                }
            }
        }
    }
    fun waitTillListIsFilled(numElements: Int, list: ArrayList<Pair<GroupWithId, ArrayList<LightWithId>>>, newElement: Pair<GroupWithId, ArrayList<LightWithId>>, api: HueApi){
        list.add(newElement)
        if (list.size == numElements){
            val listView = findViewById<ExpandableListView>(R.id.expandedList)
            listView.setAdapter(CustomListAdapter(this, list, resources, api))
        }
    }
}