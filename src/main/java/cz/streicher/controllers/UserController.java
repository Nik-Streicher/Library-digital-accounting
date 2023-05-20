package cz.streicher.controllers;


import cz.streicher.dao.UserDAO;
import cz.streicher.models.User;
import cz.streicher.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class UserController {

    private final UserDAO userDAO;
    private final UserValidator userValidator;


    @Autowired
    public UserController(UserDAO userDAO, UserValidator userValidator) {
        this.userDAO = userDAO;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        return "/users/index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        model.addAttribute("books", userDAO.getUserBooks(id));
        return "/users/get";
    }

    @RequestMapping("/new")
    public String add(Model model) {
        model.addAttribute("emptyUser", new User());
        return "/users/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("emptyUser") @Valid User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/users/create";
        userDAO.add(user);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.get(id));
        return "/users/edit";
    }


    @PatchMapping("/{id}")
    public String change(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {

        userValidator.validate(user, bindingResult);


        if (bindingResult.hasErrors())
            return "/users/edit";
        userDAO.edit(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/people";
    }
}
