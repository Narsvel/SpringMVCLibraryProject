package org.ost.springlibraryproject.controllers;

import jakarta.validation.Valid;
import org.ost.springlibraryproject.dao.BookDAO;
import org.ost.springlibraryproject.dao.PersonDAO;
import org.ost.springlibraryproject.models.Person;
import org.ost.springlibraryproject.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO,BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        //в этом методе получаем всех людей из DAO при GET запросе на /people и передадим на отображение
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap modelMap) {
        //в этом методе получаем одного человека из DAO при GET запросе на /people/{id} и передадим на отображение
        modelMap.addAttribute("person", personDAO.show(id));
        modelMap.addAttribute("books", bookDAO.indexPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){ //можно использовать Model и самим внедрять model.addAttribute("person", new Person())
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, //@Valid проверяет валидность значений полей объекта Person
                         BindingResult bindingResult) { //BindingResult обязательно должен идти после валидируемого объекта
        personValidator.validate(person, bindingResult); //проверка валидности в PersonValidator

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people"; //после добавления Person в БД произойдет переход на /people
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult); //проверка валидности в PersonValidator

        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }


}
