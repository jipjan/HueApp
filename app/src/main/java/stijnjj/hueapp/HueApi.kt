package stijnjj.hueapp

import android.content.Context

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by Jaap-Jan on 21-11-2017.
 */

class HueApi(c: Context) {

    internal var _queue: RequestQueue

    init {
        _queue = Volley.newRequestQueue(c)
    }

    fun getLights() {

    }
}
