package stijnjj.hueapp

import android.content.Context
import android.util.Log
import com.android.volley.Request

import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import org.json.JSONObject
import stijnjj.hueapp.Json.GroupClasses.Group
import stijnjj.hueapp.Json.GroupClasses.SingleGroup
import stijnjj.hueapp.Json.LightClasses.Light
import stijnjj.hueapp.Json.LightClasses.LightSettings
import kotlin.jvm.javaClass

/**
 * Created by Jaap-Jan on 21-11-2017.
 */

class HueApi(c: Context) {

    internal var _queue: RequestQueue

    internal var _settings = Settings.getInstance()

    init {
        _queue = Volley.newRequestQueue(c)
    }

    fun getLights(onDone: (lights: ArrayList<Light>) -> Unit) {
        getListCall("lights", onDone)
    }

    fun getGroups(onDone: (lights: ArrayList<Group>) -> Unit) {
        getListCall("groups", onDone)
    }

    fun getGroupInfo(id: Int, onDone: (group: SingleGroup) -> Unit) {
        getSingleCall("groups/" + id, onDone)
    }

    fun getInfoLight(id: Int, onDone: (light: Light) -> Unit) {
        getSingleCall("lights/" + id, onDone)
    }

    fun setLightState(id: Int, settings: LightSettings) {
        putCall("lights/$id/state", settings)
    }

    fun setGroupState(id: Int, settings: LightSettings) {
        putCall("groups/$id/action", settings)
    }

    private inline fun <reified TResponse> getListCall(subDir: String, crossinline onDone: (lights: ArrayList<TResponse>) -> Unit) {
        var url = makeUrl(subDir)
        var request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> {
            response ->
            val lights = ArrayList<TResponse>()
            val itt = response.keys()
            val gson = GsonBuilder().create()
            while (itt.hasNext()) {
                var str = response.getJSONObject(itt.next())
                lights.add(gson.fromJson(str.toString(), TResponse::class.java))
            }
            onDone(lights)
        }, Response.ErrorListener {
            response ->
            Log.d("API", "Failed")
        })
        _queue.add(request)
    }

    private inline fun <reified TResponse> getSingleCall(subDir: String, crossinline onDone: (item: TResponse) -> Unit) {
        var url = makeUrl(subDir)
        var request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> {
            response ->
            val gson = GsonBuilder().create()
            onDone(gson.fromJson(response.toString(), TResponse::class.java))
        }, Response.ErrorListener {
            response ->
            Log.d("API", "Failed")
        })
        _queue.add(request)
    }

    private inline fun <reified TPut> putCall(subDir: String, settings: TPut) {
        var url = makeUrl(subDir)
        val gson = GsonBuilder().create()
        val json = gson.toJson(settings)
        var request = JsonObjectRequest(Request.Method.PUT, url, JSONObject(json) , Response.Listener<JSONObject> {
            response ->
            Log.d("API", "Success")
        }, Response.ErrorListener {
            response ->
            Log.d("API", "Failed")
        })
        _queue.add(request)
    }

    private fun makeUrl(subDir: String): String =
            "http://" + _settings.location + ":" + _settings.port + "/api/" + _settings.username + "/" + subDir
}
