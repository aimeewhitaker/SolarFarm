package solarfarm.model;

import javax.validation.constraints.*;

public class Panel {

    @NotNull
    private int panelId;

    @NotBlank(message = "Section name is required.")
    @Size(max = 50, message = "Section name cannot be greater than 50 characters.")
    private String section;

    @Min(value = 1, message = "Row must be between 1 and 250.")
    @Max(value = 250, message = "Row must be between 1 and 250.")
    private int row;

    @Min(value = 1, message = "Col must be between 1 and 250.")
    @Max(value = 250, message = "Col must be between 1 and 250.")
    private int col;

    @Min(value = 1900, message = "Year installed must be in the past.")
    @Max(value = 2020, message = "Year installed must be in the past.")
    private int yearInstalled;

    @Min(value = 0, message = "Type must be between 0 and 5.")
    @Max(value = 5, message = "Type must be between 0 and 5.")
    private int type;

    @NotNull
    private boolean tracked;

    public Panel(String section, int row, int col, int yearInstalled, int type, boolean isTracking) {
        this.section = section;
        this.row = row;
        this.col = col;
        this.yearInstalled = yearInstalled;
        this.type = type;
        this.tracked = isTracking;
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

    public boolean getTracked() {
        return tracked;
    }

    public void setTracked(boolean tracked) {
        this.tracked = tracked;
    }
}
