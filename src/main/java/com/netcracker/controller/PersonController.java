package com.netcracker.controller;

import com.netcracker.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class PersonController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/addDataPage")
    public String addPersonForm(Model model){
        model.addAttribute("person",new Person());
        return "addDataPage";
    }


    @PostMapping("/addDataPage")
    public String personSubmit(@ModelAttribute Person person)throws IOException{

        //делаю Серелизацию
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\tema_\\Documents\\" +
                    person.getSurname() + person.getName() + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Сохраню в txt вайл для удобства чтения
        FileWriter fileWriter=new FileWriter("C:/Users/tema_/Documents/docTemaNew.txt");
        fileWriter.write(
                String.valueOf(person.getSurname())+" "+
                        String.valueOf(person.getName())+" "+
                        String.valueOf(person.getPatronymic())+" "+
                        String.valueOf(person.getAge())+" "+
                        String.valueOf(person.getSalary())+" "+
                        String.valueOf(person.getEmailAdress())+" "+
                        String.valueOf(person.getWorkPlace())

        );
        fileWriter.close();

        return "index";
    }




        //Десерилизация


    @GetMapping("/find")
    public String findDataPerson(Model model) {
        model.addAttribute("person",new Person());
        return "searchPerson";
    }


    @GetMapping("/find-serch")
    public String searchDataPerson(
            @ModelAttribute Person person,Model model)throws ClassNotFoundException,IOException {
        /*
            try {
                FileInputStream fileInputStream = new FileInputStream("C:\\Users\\tema_\\Documents\\" +
                        person.getSurname() + person.getName() + ".ser");
                ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
                Person person1 = (Person) objectInput.readObject();
                person.setAge(30);
                //System.out.println(person1.getAge());
                //model.addAttribute("person", person);
            } catch (IOException | ClassNotFoundException e) {
               // return "notFound";
            }

         */
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\tema_\\Documents\\" +
                person.getSurname() + person.getName() + ".ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
        Person person1 = (Person) objectInput.readObject();
        person.setAge(30);
        model.addAttribute("person", person1);
        return "personDataPage";

    }

    /*
    @PostMapping("/index")
    public String personSubmit(@ModelAttribute Person person){
        return "personDataPage";
    }

     */

}
