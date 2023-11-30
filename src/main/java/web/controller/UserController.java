package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "index";
    }

    @GetMapping("/users")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
            return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";

        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";

        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") int id) {
        userService.delete(id);
        return "redirect:/";
    }
}