package stijnjj.hueapp.Json.GroupClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleGroup {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("action")
    @Expose
    private AdvancedAction action;
    @SerializedName("lights")
    @Expose
    private List<String> lights = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AdvancedAction getAction() {
        return action;
    }

    public void setAction(AdvancedAction action) {
        this.action = action;
    }

    public List<String> getLights() {
        return lights;
    }

    public void setLights(List<String> lights) {
        this.lights = lights;
    }

}
