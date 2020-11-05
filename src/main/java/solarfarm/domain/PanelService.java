package solarfarm.domain;


import org.springframework.stereotype.Service;
import solarfarm.data.PanelRepository;
import solarfarm.model.Panel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        Result<Panel> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Panel>> violations = validator.validate(panel);

        if (violations.size() > 0) {
            for (ConstraintViolation<Panel> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            result.addMessage("Uh this didn't work", ResultType.INVALID);
        } else {
            Panel p = repository.add(panel);
            result.setPayload(p);
            return result;
        }
        return result;
    }


    //can not change location of panel
    public Result<Panel> update(Panel panel) {
        Result<Panel> result = new Result<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Panel>> violations = validator.validate(panel);

        if (violations.size() > 0) {
            for (ConstraintViolation<Panel> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            result.addMessage("Uh this didn't work", ResultType.INVALID);
        } else {
            Panel existing = repository.findById(panel.getPanelId());
            if (existing != null) {
                boolean success = repository.update(panel);
                if (success) {
                    return result;
                }
                result.addMessage("Uh oh", ResultType.INVALID);
            }
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
