package org.ost.springlibraryproject.controllers;

import jakarta.validation.Valid;
import org.ost.springlibraryproject.dao.BookDAO;
import org.ost.springlibraryproject.dao.PersonDAO;
import org.ost.springlibraryproject.models.Book;
import org.ost.springlibraryproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    private final int NULLBOOKPERSONID = 0;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        //в этом методе получаем все книги из DAO при GET запросе на /books и передадим на отображение
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap modelMap, @ModelAttribute("person") Person person) {
        //в этом методе получаем одну книгу из DAO при GET запросе на /books/{id} и передадим на отображение
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("people", personDAO.index());
        modelMap.addAttribute("personup", personDAO.show((bookDAO.show(id).isPersonId() ? NULLBOOKPERSONID : bookDAO.show(id).getPersonId())));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){ //можно использовать Model и самим внедрять model.addAttribute("book", new Book())
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, //@Valid проверяет валидность значений полей объекта Book
                         BindingResult bindingResult) { //BindingResult обязательно должен идти после валидируемого объекта

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books"; //после добавления Book в БД произойдет переход на /books
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult, @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/addPerson/{id}")
    public String addBookPersonId(@ModelAttribute("person") Person person,
                                  @PathVariable("id") int id) {
        bookDAO.addBookPersonId(id, person.getPersonId());
        return "redirect:/books/" + id;
    }

    @PatchMapping("/deletePerson/{id}")
    public String deleteBookPersonId(@ModelAttribute("person") Person person,
                                     @PathVariable("id") int id) {
        bookDAO.deleteBookPersonId(id);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}
