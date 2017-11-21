
package stijnjj.hueapp.Json.GroupClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("xy")
    @Expose
    private List<Double> xy = null;
    @SerializedName("ct")
    @Expose
    private Integer ct;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("sat")
    @Expose
    private Integer sat;
    @SerializedName("bri")
    @Expose
    private Integer bri;
    @SerializedName("hue")
    @Expose
    private Integer hue;
    @SerializedName("colormode")
    @Expose
    private String colormode;
    @SerializedName("on")
    @Expose
    private Boolean on;

    public List<Double> getXy() {
        return xy;
    }

    public void setXy(List<Double> xy) {
        this.xy = xy;
    }

    public Integer getCt() {
        return ct;
    }

    public void setCt(Integer ct) {
        this.ct = ct;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public Integer getBri() {
        return bri;
    }

    public void setBri(Integer bri) {
        this.bri = bri;
    }

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {
        this.hue = hue;
    }

    public String getColormode() {
        return colormode;
    }

    public void setColormode(String colormode) {
        this.colormode = colormode;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

}
