package solarfarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import solarfarm.domain.PanelService;

import java.time.LocalDateTime;

@Controller
public class SolarFarmUIController {

    @Autowired
    PanelService service;

    @GetMapping("/")
    public String getHomeView() {
        return "home";
    }

    @GetMapping("/findAll")
    public String getEncouragement(Model model) {
        model.addAttribute("panels", service.findAll());
        return "findAll";
    }



}
