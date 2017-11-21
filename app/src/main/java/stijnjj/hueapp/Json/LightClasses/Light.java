
package stijnjj.hueapp.Json.LightClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Light {

    @SerializedName("modelid")
    @Expose
    private String modelid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("swversion")
    @Expose
    private String swversion;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pointsymbol")
    @Expose
    private Pointsymbol pointsymbol;
    @SerializedName("uniqueid")
    @Expose
    private String uniqueid;

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwversion() {
        return swversion;
    }

    public void setSwversion(String swversion) {
        this.swversion = swversion;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pointsymbol getPointsymbol() {
        return pointsymbol;
    }

    public void setPointsymbol(Pointsymbol pointsymbol) {
        this.pointsymbol = pointsymbol;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

}
