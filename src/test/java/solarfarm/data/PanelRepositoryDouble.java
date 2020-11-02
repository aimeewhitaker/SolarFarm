package solarfarm.data;

import solarfarm.model.Panel;


import java.util.ArrayList;
import java.util.List;

public class PanelRepositoryDouble implements PanelRepository{

    ArrayList<Panel> panels = new ArrayList<>();


    @Override
    public List<Panel> findAll(){
        return new ArrayList<>(panels);
    }

    @Override
    public Panel findById(int panelId){
        for (Panel p : panels) {
            if (p.getPanelId() == panelId) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Panel> findBySection(String section){
        List<Panel> sectionPanels = new ArrayList<>();
        for (Panel p : panels) {
            if (p.getSection().equals(section)) {
                sectionPanels.add(p);
            }
        }
        return sectionPanels;
    }

    @Override
    public Panel add(Panel panel){
        panels.add(panel);
        return panel;
    }

    @Override
    public boolean update(Panel panel){
        return findById(panel.getPanelId()) != null;
    }

    @Override
    public boolean remove(int panelId){
        return panels.remove(findById(panelId));
    }
}
