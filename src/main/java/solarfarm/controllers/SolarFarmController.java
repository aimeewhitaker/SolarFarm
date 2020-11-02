package solarfarm.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solarfarm.domain.Result;
import solarfarm.domain.ResultType;
import solarfarm.domain.PanelService;
import solarfarm.model.Panel;

import java.util.List;

@RestController          // 1. Spring DI and MVC
@RequestMapping("/solar_farm") // 2. Base URL
public class SolarFarmController {

    private final PanelService service;

    public SolarFarmController(PanelService service) {
        this.service = service;
    }
/*
    @GetMapping("/{section}")
    public List<Panel> findBySection(@PathVariable String section) throws DataAccessException {
        return service.findBySection(section);
    }*/

    @GetMapping
    public List<String> findAllSections(){
        return service.findAllSections();
    }

    @GetMapping("/all")
    public List<Panel> findAll(){
        return service.findAll();
    }

    @GetMapping("/{panelId}")
    public ResponseEntity<Panel> findById(@PathVariable int panelId) {
        Panel panel = service.findPanelById(panelId);
        if (panel == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(panel, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Panel> add(@RequestBody Panel panel) {
        Result<Panel> result = service.add(panel);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
}
