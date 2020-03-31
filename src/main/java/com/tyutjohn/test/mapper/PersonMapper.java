package com.tyutjohn.test.mapper;

import com.tyutjohn.test.bean.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    List<Person> selectPerson();
    void insertPerson(@Param("person")Person person);
}
