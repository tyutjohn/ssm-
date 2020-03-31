package com.tyutjohn.test.service.Impl;

import com.tyutjohn.test.bean.Person;
import com.tyutjohn.test.mapper.PersonMapper;
import com.tyutjohn.test.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonImpl implements PersonService {
    @Resource
    PersonMapper personMapper;
    public List<Person> getAllPerson(){
        return personMapper.selectPerson();
    }
    public String addPerson(Person person){
        try {
            personMapper.insertPerson(person);
            return "success";
        }catch (Exception e){
            return ("error:\n"+e);
        }
    }
}
