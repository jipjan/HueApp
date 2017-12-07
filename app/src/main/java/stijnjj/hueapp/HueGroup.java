package stijnjj.hueapp;

import java.util.ArrayList;

/**
 * Created by Jaap-Jan on 7-12-2017.
 */

public class HueGroup extends ExpandableGroup {
    public HueGroup(GroupWithId group, ArrayList<LightWithId> lights) {
        super(group, lights);
    }

    @Override
    public void updateLights() {

    }
}
