package cz.streicher.controllers;


import cz.streicher.dao.BookDAO;
import cz.streicher.dao.UserDAO;
import cz.streicher.models.Book;
import cz.streicher.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final UserDAO userDAO;

    @Autowired
    public BookController(BookDAO bookDAO, UserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model, @ModelAttribute("person") User user) {
        model.addAttribute("book", bookDAO.get(id));
        model.addAttribute("loan", bookDAO.getLoan(id));
        model.addAttribute("users", userDAO.index());
        return "/books/get";
    }

    @RequestMapping("/new")
    public String add(Model model) {
        model.addAttribute("emptyBook", new Book());
        return "/books/create";
    }

    @PostMapping()
    public String create(@ModelAttribute() Book book) {
        bookDAO.add(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.get(id));
        return "/books/edit";
    }


    @PatchMapping("/{id}")
    public String change(@ModelAttribute() Book book, @PathVariable("id") int id) {
        bookDAO.edit(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}/release")
    public String release(@PathVariable int id) {
        bookDAO.deleteUser(id);
        return String.format("redirect:/books/%d", id);
    }

    @PatchMapping("{id}/appoint")
    public String appoint(@PathVariable("id") int bookId, @ModelAttribute("user") User user) {
        bookDAO.appoint(user.getUser_id(), bookId);
        return String.format("redirect:/books/%d", bookId);
    }
}
