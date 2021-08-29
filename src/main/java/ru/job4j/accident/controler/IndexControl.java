package ru.job4j.accident.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexControl {

    private final AccidentHibernate accidents;

    public IndexControl(AccidentHibernate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidentList = accidents.findAllAccidentsWithRules();
        model.addAttribute("accidents", accidentList);
        return "index";
    }

    @GetMapping("/createAccidentForm")
    public String createAccidentForm(Model model) {

        Collection<AccidentType> types = accidents.findAllAccidentTypes();
        model.addAttribute("types", types);

        Collection<Rule> rules = accidents.findAllRules();
        model.addAttribute("rules", rules);

        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rIds = req.getParameterValues("rIds");
        accidents.saveAccident(accident, rIds);
        return "redirect:/";
    }
//
//    @GetMapping("/updateAccidentForm")
//    public String updateAccidentForm(@RequestParam("id") int id, Model model) {
//
//        Optional<Accident> accident = accidents.findAccidentById(id);
//        accident.ifPresent(value -> model.addAttribute("accident", value));
//
//        Collection<AccidentType> types = accidents.findAllAccidentTypes();
//        model.addAttribute("types", types);
//
//        Collection<Rule> rules = accidents.findAllRules();
//        model.addAttribute("rules", rules);
//
//        return "accident/update";
//    }
//
//    @PostMapping("/updateAccident")
//    public String updateAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
//        String[] rIds = req.getParameterValues("rIds");
//        accidents.updateAccident(accident, rIds);
//        return "redirect:/";
//    }
}
