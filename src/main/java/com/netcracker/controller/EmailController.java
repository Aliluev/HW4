package com.netcracker.controller;

import com.netcracker.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Controller
public class EmailController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/emailPage")
    public String getPage(Model model){
        model.addAttribute("person",new Person());
        return "submitEmail";
    }



    @GetMapping("/submitEmail")
    public String sendSimpleEmail(@RequestParam("massage") String myMassage,
                                  @ModelAttribute Person person, Model model)
            throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\tema_\\Documents\\"
                    + person.getSurname() + person.getName() + ".ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInputStream);
            Person person1 = (Person) objectInput.readObject();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(person1.getEmailAdress());
            message.setSubject("Это я тебе отправил письмо со Spring");
            //Письмо которое мне ввели
            message.setText(myMassage);
             this.emailSender.send(message);
        } catch (IOException | ClassNotFoundException e) {
            //return "notFound";
        }
        return "index";


    }
}
