package com.tyutjohn.test.controller;

import com.tyutjohn.test.bean.Person;
import com.tyutjohn.test.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PersonController {
    @Resource
    PersonService personService;
    @RequestMapping(value = "/getAllPerson",method = RequestMethod.GET)
    @ResponseBody
    public List<Person> GetAllPerson(){
        List<Person> personList=personService.getAllPerson();
        return personList;
    }
    @RequestMapping(value = "/addPerson",method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody Person person){
        String result=personService.addPerson(person);
        return result;
    }
}
