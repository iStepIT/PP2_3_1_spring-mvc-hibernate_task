package web.controller;

import web.model.Person;
import web.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class PeopleController {
    private final PersonService personService;

    public PeopleController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("persons", personService.index());
        return "/index";
    }

    @GetMapping("/show")
    public String show(@RequestParam(name = "id") int id, Model model) {
        model.addAttribute("person", personService.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        personService.save(person);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") int id) {
        model.addAttribute("person", personService.show(id));
        return "edit";
    }

    @PostMapping("/")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @RequestParam(name = "id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        personService.update(id, person);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") int id) {
        personService.delete(id);
        return "redirect:/";
    }
}
