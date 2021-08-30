package ru.job4j.accident.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {

    private final AccidentRepository accidents;

    private final AccidentTypeRepository accidentTypes;

    private final AccidentRuleRepository accidentRules;

    private final AccidentService accidentService;

    public IndexControl(AccidentRepository accidents, AccidentTypeRepository accidentTypes, AccidentRuleRepository accidentRules,
                        AccidentService accidentService) {
        this.accidents = accidents;
        this.accidentTypes = accidentTypes;
        this.accidentRules = accidentRules;
        this.accidentService = accidentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> res = new ArrayList<>();
        accidents.findAll().forEach(res::add);
        model.addAttribute("accidents", res);
        return "index";
    }

    @GetMapping("/createAccidentForm")
    public String createAccidentForm(Model model) {

        List<AccidentType> types = new ArrayList<>();
        accidentTypes.findAll().forEach(types::add);
        model.addAttribute("types", types);

        List<Rule> rules = new ArrayList<>();
        accidentRules.findAll().forEach(rules::add);
        model.addAttribute("rules", rules);

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

        List<AccidentType> types = new ArrayList<>();
        accidentTypes.findAll().forEach(types::add);
        model.addAttribute("types", types);

        List<Rule> rules = new ArrayList<>();
        accidentRules.findAll().forEach(rules::add);
        model.addAttribute("rules", rules);

        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidentService.saveAccident(accident, rIds);
        return "redirect:/";
    }
}
