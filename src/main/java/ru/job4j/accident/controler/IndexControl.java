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
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Controller
public class IndexControl {

//    private AccidentService accidentService;

    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

//    public IndexControl(AccidentService accidentService) {
//        this.accidentService = accidentService;
//    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.findAllAccidentsWithRules());
        return "index";
    }

    @GetMapping("/createAccident")
    public String create(Model model) {

        Collection<AccidentType> types = accidents.findAllAccidentTypes();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidents.findAllRules();
        model.addAttribute("rules", rules);

        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidents.saveAccident(accident, rIds);
        return "redirect:/";
    }

    @GetMapping("/updateAccident")
    public String update(@RequestParam("id") int id, Model model) {

        Optional<Accident> accident = accidents.findAccidentById(id);
        accident.ifPresent(value -> model.addAttribute("accident", value));

        Collection<AccidentType> types = accidents.findAllAccidentTypes();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidents.findAllRules();
        model.addAttribute("rules", rules);

        return "accident/update";
    }

//    @GetMapping("/")
//    public String index(Model model) {
//        Collection<Accident> accidents = accidentService.findAllAccidents();
//        model.addAttribute("accidents", accidents);
//        return "index";
//    }
}
