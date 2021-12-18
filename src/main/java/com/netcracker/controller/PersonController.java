package com.netcracker.controller;

import com.netcracker.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class PersonController {

    @GetMapping("/addDataPage")
    public String addPersonForm(Model model){
        model.addAttribute("person",new Person());
        return "addDataPage";
    }


    @PostMapping("/addDataPage")
    public String personSubmit(@ModelAttribute Person person)throws IOException{
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\tema_\\Documents\\" +
                    person.getSurname() + person.getName() + ".ser");


            //String a = String.valueOf(1);

            FileWriter fileWriter=new FileWriter("C:/Users/tema_/Documents/docTemaNew.txt");
          //  FileWriter fileWriter=new FileWriter("com/netcracker/docTemaNew.txt");
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

            /*
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);

             */

/*
            Files.createFile(Path.of("c:\\readme.txt"));
            Files.createFile(Path.of("C:\\Users\\tema_\\Documents\\")

 */

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "personDataPage";
    }

    /*
    @PostMapping("/index")
    public String personSubmit(@ModelAttribute Person person){
        return "personDataPage";
    }

     */

}
