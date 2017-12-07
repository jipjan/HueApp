package stijnjj.hueapp;

import java.util.ArrayList;

import stijnjj.hueapp.Json.GroupClasses.SingleGroup;

/**
 * Created by Jaap-Jan on 7-12-2017.
 */

public abstract class ExpandableGroup {

    GroupWithId group;
    ArrayList<LightWithId> lights;

    public ExpandableGroup(GroupWithId group, ArrayList<LightWithId> lights) {
        this.group = group;
        this.lights = lights;
    }

    public GroupWithId getGroup() {
        return group;
    }

    public void setGroup(GroupWithId group) {
        this.group = group;
    }

    public ArrayList<LightWithId> getLights() {
        return lights;
    }

    public void setLights(ArrayList<LightWithId> lights) {
        this.lights = lights;
    }

    public abstract void updateLights();
}
