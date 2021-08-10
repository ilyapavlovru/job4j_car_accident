package ru.job4j.accident.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentService;

import java.util.Collection;

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
        model.addAttribute("accidents", accidents.getAllAccidentsWithRules());
        return "index";
    }

//    @GetMapping("/")
//    public String index(Model model) {
//        Collection<Accident> accidents = accidentService.findAllAccidents();
//        model.addAttribute("accidents", accidents);
//        return "index";
//    }
}
