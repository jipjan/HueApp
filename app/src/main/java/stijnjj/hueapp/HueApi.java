package stijnjj.hueapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Jaap-Jan on 21-11-2017.
 */

public class HueApi {

    RequestQueue _queue;

    public HueApi(Context c) {
        _queue = Volley.newRequestQueue(c);
    }

    public void getLights() {

    }

}
