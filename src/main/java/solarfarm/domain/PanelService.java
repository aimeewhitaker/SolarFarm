package solarfarm.domain;


import org.springframework.stereotype.Service;
import solarfarm.data.PanelRepository;
import solarfarm.model.Panel;
import java.util.ArrayList;
import java.util.List;

@Service
public class PanelService {

    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findAll() {
        return repository.findAll();
    }

    public Result<Panel> add(Panel panel){
        Result result = validateInputs(panel);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        //check for duplicate s
        result = validateLocation(panel);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }

        Panel p = repository.add(panel);
        result.setPayload(p);
        return result;
    }

    private Result<Panel> validateInputs(Panel panel){
        Result result = new Result();
        if (panel == null) {
            result.addMessage("Panel cannot be null", ResultType.NOT_FOUND);
            return result;
        }

        if (panel.getRow()  < 0 || panel.getRow() > 250
                || panel.getCol() < 0 || panel.getCol() > 250) {
            result.addMessage("Panel must be in col/row between 0 and 250", ResultType.INVALID);
        }

        if (panel.getSection() == null || panel.getSection().length() == 0) {
            result.addMessage("Section must have a name", ResultType.INVALID);
        }

        if (panel.getYearInstalled() > 2020) {
            result.addMessage("Panel must be placed in the past", ResultType.INVALID);
        }
        return result;
    }

    private Result validateLocation(Panel panel){
        Result result = new Result();

        List<Panel> allPanels = repository.findAll();

        for (Panel p : allPanels) {
            if (p.getRow() == panel.getRow() && panel.getCol() == p.getCol()
            && p.getSection().equals(panel.getSection())) {
                result.addMessage("Duplicate panel", ResultType.INVALID);
            }
        }
        return result;
    }

    //can not change location of panel
    public Result update(Panel panel) {
        Result result = validateInputs(panel);
        if (result.getType() != ResultType.SUCCESS) {
            return result;
        }
        Panel existing = repository.findById(panel.getPanelId());
        if (existing == null) {
            result.addMessage("Panel Id " + panel.getPanelId() + " not found.", ResultType.NOT_FOUND);
            return result;
        }
//        if (!(existing.getSection().equals(panel.getSection())) || existing.getCol() != panel.getRow()
//            || existing.getCol() != panel.getCol()) {
//            result.addErrorMessage("Cannot update location of panel.");
//            return result;
//        }
        boolean success = repository.update(panel);
        if (!success) {
            result.addMessage("Could not update.", ResultType.INVALID);
        }
        return result;
    }

    public Result remove(int panelId){
        Result result = new Result();
        Panel panel = repository.findById(panelId);
        if (panel == null) {
            result.addMessage("Could not find Panel Id: " + panelId, ResultType.NOT_FOUND);
            return result;
        }

        boolean success = repository.remove(panelId);
        if (!success) {
            result.addMessage("Could not remove Panel", ResultType.INVALID);
        }
        return result;
    }

    public List<Panel> findBySection(String section) {
        List<Panel> sections = repository.findBySection(section);
        return sections;
    }

    public Panel findPanelById(int panelId) {
        for (Panel p : repository.findAll()) {
            if (p.getPanelId() == panelId) {
                return p;
            }
        }
        return null;
    }

    public List<String> findAllSections() {
        List<String> allSections = new ArrayList<>();
        for (Panel p : repository.findAll()) {
            if (!(allSections.contains(p.getSection()))) {
                allSections.add(p.getSection());
            }
        }
        return allSections;
    }
}
