package org.ost.springlibraryproject.util;

import org.ost.springlibraryproject.dao.PersonDAO;
import org.ost.springlibraryproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        //смотрим есть ли человек с таким email в БД
        if (personDAO.show(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Это ФИО уже используется.");
        //помещаем в errors ошибку 1 - поле ошибки, 2 - код ошибки (сейчас не используем), 3 - сообщение ошибки
    }
}
