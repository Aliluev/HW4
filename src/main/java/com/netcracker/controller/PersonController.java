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
import java.util.Scanner;

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
        FileWriter fileWriter=new FileWriter("C:/Users/tema_/Documents/"+person.getSurname() + person.getName()+".txt");
        fileWriter.write(String.valueOf(person.getSurname())+" "+
                           String.valueOf(person.getName())+" "+
                           String.valueOf(person.getPatronymic())+" "+
                           String.valueOf(person.getAge())+" "+
                           String.valueOf(person.getSalary())+" " +
                           String.valueOf(person.getEmailAdress())+" "+
                           String.valueOf(person.getWorkPlace())

        );
        fileWriter.close();

        return "personDataPage";
    }

    //Десерилизация
    @GetMapping("/load")
    public String findDataPerson(Model model) {
        model.addAttribute("person",new Person());
        return "searchPerson";
    }

    @GetMapping("/load-serch")
    public String searchDataPerson(
            @ModelAttribute Person person,Model model)throws ClassNotFoundException,IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\tema_\\Documents\\" +
                person.getSurname() + person.getName() + ".ser");
        ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
        Person person1 = (Person) objectInput.readObject();
        model.addAttribute("person", person1);
        return "personDataPage";

    }

    @GetMapping("/find")
    public String findPerson(Model model) {
        model.addAttribute("person",new Person());
        return "findPersonByFile";
    }


    @GetMapping("/find-file")
    public String FindByFile(@RequestParam("file") String file, Model model) throws FileNotFoundException {
        //public static boolean addFromFile(Path path) throws IOException {


        // File f = new File("UserFile.txt");
        FileReader reader = new FileReader(new File("C:\\Users\\tema_\\Documents\\"+file+".txt"));
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] data = nextLine.split(" ");
                System.out.println(file);
                System.out.println(data.length);
                System.out.println(data[0]);
                //Создадим объект, запишем в него данные, а потом сереализуем
                // так как логика у меня заточена на сериализация
                if (data.length == 7) {
                    Person person = new Person();
                    person.setSurname(data[0]);
                    person.setName(data[1]);
                    person.setPatronymic(data[3]);
                    person.setAge(Integer.parseInt(data[4]));
                    person.setEmailAdress(data[5]);
                    person.setWorkPlace(data[6]);
                    model.addAttribute("person",person);
                    //сериалезуем
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\tema_\\Documents\\" +
                                person.getSurname() + person.getName() + ".ser");
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                        objectOutputStream.writeObject(person);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    return "falseDataPage";
                }

            return "personDataPage";
        }
        /*
        try (FileReader reader = new FileReader("notes3.txt")) {
            // читаем посимвольно
            int c;
            while ((c = reader.read()) != -1) {

                System.out.print((char) c);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

         */

        return "personDataPage";
    }



}
