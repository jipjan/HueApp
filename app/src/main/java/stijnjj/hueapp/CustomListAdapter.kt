package stijnjj.hueapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.onegravity.colorpicker.ColorPickerDialog
import com.onegravity.colorpicker.ColorPickerListener
import com.onegravity.colorpicker.SetColorPickerListenerEvent
import stijnjj.hueapp.Json.GroupClasses.Group
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import stijnjj.hueapp.Json.LightClasses.LightSettings
import kotlin.collections.HashMap


class CustomListAdapter(private val context: Context, private val groups: HashMap<Group, ArrayList<LightWithId>>,private val resources: Resources,private val api: HueApi) : BaseExpandableListAdapter() {

    override fun getChildrenCount(p0: Int) = groups[getGroup(p0)]!!.size

    override fun getGroup(p0: Int): Group = groups.entries.elementAt(p0).key

    override fun getChild(p0: Int, p1: Int): LightWithId = groups[getGroup(p0)]!![p1]

    override fun getGroupId(p0: Int): Long = p0.toLong()

    override fun isChildSelectable(p0: Int, p1: Int): Boolean = false

    override fun hasStableIds() = false

    override fun getChildView(groupPos: Int, childPos: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {

        val newView: View
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        newView = layoutInflater.inflate(R.layout.list_detail, parent, false)
        val light = getChild(groupPos, childPos)
        println(((light.light.state.hue.toFloat() / 65535) * 360))
        println(light.light.state.hue)
        var lightColor = Color.HSVToColor(floatArrayOf(((light.light.state.hue.toFloat() / 65535) * 360), (light.light.state.sat.toFloat() / 254), (light.light.state.bri / 254).toFloat()))

        val switch = newView.findViewById<Switch>(R.id.switchOnLight)
        switch.isChecked = light.light.state.on
        switch.setOnCheckedChangeListener({ _, isChecked ->
            light.light.state.on = isChecked
            api.setLightState(light.id, LightSettings(light.light.state.on, light.light.state.hue, light.light.state.sat, light.light.state.bri))
        })

        val text = newView.findViewById<TextView>(R.id.txtLightName)
        text.text = light.light.name

        val colorButton = newView.findViewById<ImageView>(R.id.lightColorBtn)
        colorButton.setColorFilter(lightColor)
        colorButton.setOnClickListener {
            val dialog = ColorPickerDialog(context, lightColor, false)
            var dialogId = dialog.show()
            SetColorPickerListenerEvent.setListener(dialogId,
                    object : ColorPickerListener {
                        var shouldBeUpdated = false
                        var lightSettings: LightSettings
                        val scheduledTask = Executors.newScheduledThreadPool(1)
                        val task = object : TimerTask(){
                            override fun run() {
                                shouldBeUpdated = true
                                println("update = true")
                            }
                        }

                        init{
                            scheduledTask.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS)
                            lightSettings = LightSettings()
                            lightSettings.isOn = light.light.state.on
                        }

                        override fun onColorChanged(color: Int) {
                            val hsv = floatArrayOf(0f,0f,0f)
                            Color.colorToHSV(color, hsv)

                            lightSettings.hue = (hsv[0] / 360 * 65535).toInt()
                            lightSettings.saturation = (hsv[1] * 254).toInt()
                            lightSettings.brightness = (hsv[2] * 254).toInt()
                            lightColor = color

                            if (shouldBeUpdated) {
                                api.setLightState(light.id, lightSettings)
                                light.light.state.hue = (hsv[0] / 360 * 65535).toInt()
                                light.light.state.sat = (hsv[1] * 254).toInt()
                                light.light.state.bri = (hsv[2] * 254).toInt()
                                colorButton.setColorFilter(color)
//                                println("update = false")
                                shouldBeUpdated = false
                            }
                        }

                        override fun onDialogClosing() {
                            scheduledTask.shutdown()
                            shouldBeUpdated = true
                            dialogId = -1
                         //   notifyDataSetChanged()
                        }
                    }
            )
        }
        return newView
    }

    override fun getChildId(p0: Int, p1: Int): Long = p1.toLong()

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View? {
        val title = getGroup(p0).name
        var newView = p2
        val action = getGroup(p0).action

        if (p2 == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newView = layoutInflater.inflate(R.layout.list_header, p3, false)
        }

        if (newView != null) {
            val titleTextView = newView.findViewById<TextView>(R.id.txtGroupName)
            titleTextView.text = title
            @Suppress("DEPRECATION")
            titleTextView.setTextColor(resources.getColor(R.color.colorText))

            val colorBtn = newView.findViewById<ImageView>(R.id.groupColorBtn)
            var lightsColor = Color.HSVToColor(floatArrayOf(((action.hue.toFloat() / 65535) * 360), (action.sat.toFloat() / 254), (action.bri / 254).toFloat()))
            colorBtn.setColorFilter(lightsColor)
            colorBtn.setOnClickListener {
//                val dialog = ColorPickerDialog(context, lightsColor, false)
//                var dialogId = dialog.show()
//                SetColorPickerListenerEvent.setListener(dialogId,
//                        object : ColorPickerListener {
//                            var shouldBeUpdated = false
//                            var lightSettings: LightSettings
//                            val scheduledTask = Executors.newScheduledThreadPool(1)
//                            val task = object : TimerTask(){
//                                override fun run() {
//                                    shouldBeUpdated = true
//                                    println("update = true")
//                                }
//                            }
//
////                            init{
////                                scheduledTask.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS)
////                                lightSettings = LightSettings()
////                                lightSettings.isOn = light.light.state.on
////                            }
////
////                            override fun onColorChanged(color: Int) {
////                                val hsv = floatArrayOf(0f,0f,0f)
////                                Color.colorToHSV(color, hsv)
////
////                                lightSettings.hue = (hsv[0] / 360 * 65535).toInt()
////                                lightSettings.saturation = (hsv[1] * 254).toInt()
////                                lightSettings.brightness = (hsv[2] * 254).toInt()
////                                lightColor = color
////
////                                if (shouldBeUpdated) {
////                                    api.setLightState(light.id, lightSettings)
////                                    light.light.state.hue = (hsv[0] / 360 * 65535).toInt()
////                                    light.light.state.sat = (hsv[1] * 254).toInt()
////                                    light.light.state.bri = (hsv[2] * 254).toInt()
////                                    colorButton.setColorFilter(color)
//////                                println("update = false")
////                                    shouldBeUpdated = false
////                                }
////                            }
//
//                            override fun onDialogClosing() {
//                                scheduledTask.shutdown()
//                                shouldBeUpdated = true
//                                dialogId = -1
//                                //   notifyDataSetChanged()
//                            }
//                        }
//                )
            }
        }

        return newView
    }

    override fun getGroupCount() = groups.size

}