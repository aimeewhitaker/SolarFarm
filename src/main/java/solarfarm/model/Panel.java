package solarfarm.model;

import java.util.Arrays;

public class Panel {

    private int panelId;
    private String section;
    private int row;
    private int col;
    private int yearInstalled;
    private int type;
    boolean isTracking;

    public Panel(String section, int row, int col, int yearInstalled, int type, boolean isTracking) {
        this.section = section;
        this.row = row;
        this.col = col;
        this.yearInstalled = yearInstalled;
        this.type = type;
        this.isTracking = isTracking;
    }

    public Panel() {
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public int getType() { return type; }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }
}
