package solarfarm.model;

import java.util.List;

public enum PanelType {
    CIGS(0),
    POLYSI(1),
    CDTE(2),
    MULTICH(3),
    SI(4),
    MONOCH(5);

    private final int value;

    PanelType(final int value){
        this.value = value;
    }

    public static PanelType findByValue(int index) {
        PanelType[] types = PanelType.values();
        for (PanelType type : types) {
            if (type.getValue() == index) {
                return type;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

}
