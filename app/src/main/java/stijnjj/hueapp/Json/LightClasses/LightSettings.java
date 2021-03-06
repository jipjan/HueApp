package stijnjj.hueapp.Json.LightClasses;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Jaap-Jan on 21-11-2017.
 */

public class LightSettings {
    private boolean on;
    @Nullable
    private Integer bri, hue, sat;

    public LightSettings(boolean on, @Nullable Integer hue, @Nullable Integer sat, @Nullable Integer bri) {
        this.on = on;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
    }

    public LightSettings(){}

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getBrightness() {
        return bri;
    }

    public void setBrightness(int bri) {
        this.bri = bri;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSaturation() {
        return sat;
    }

    public void setSaturation(int sat) {
        this.sat = sat;
    }
}
