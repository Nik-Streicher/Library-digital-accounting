package cz.streicher.controllers;


import cz.streicher.dao.UserDAO;
import cz.streicher.models.Book;
import cz.streicher.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class UserController {

    private final UserDAO userDAO;


    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        System.out.println(userDAO.getUserBooks(15).isEmpty());
        for (Book book: userDAO.getUserBooks(15)) {
            System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getRelease_year());
        }
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
    public String create(@ModelAttribute() User user) {
        userDAO.add(user);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.get(id));
        return "/users/edit";
    }


    @PatchMapping("/{id}")
    public String change(@ModelAttribute() User user, @PathVariable("id") int id) {
        userDAO.edit(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/people";
    }
}
