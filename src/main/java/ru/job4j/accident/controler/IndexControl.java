package ru.job4j.accident.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentRuleService;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexControl {

    private final AccidentService accidentService;

    private final AccidentTypeService accidentTypeService;

    private final AccidentRuleService accidentRuleService;

    public IndexControl(AccidentService accidentService,
                        AccidentTypeService accidentTypeService,
                        AccidentRuleService accidentRuleService) {
        this.accidentService = accidentService;
        this.accidentTypeService = accidentTypeService;
        this.accidentRuleService = accidentRuleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidentService.findAllAccidents());
        return "index";
    }

    @GetMapping("/createAccidentForm")
    public String createAccidentForm(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", accidentRuleService.findAll());
        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setStatus("Принята");
        String[] rIds = req.getParameterValues("rIds");
        accidentService.saveAccident(accident, rIds);
        return "redirect:/";
    }

    @GetMapping("/updateAccidentForm")
    public String updateAccidentForm(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findAccidentById(id).get();
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", accidentRuleService.findAll());
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.saveAccident(accident, rIds);
        return "redirect:/";
    }
}
