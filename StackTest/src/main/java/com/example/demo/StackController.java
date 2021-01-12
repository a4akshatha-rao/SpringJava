package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class StackController {

    private StackDao stackDao;

    private final int DEFAULT_STACK_SIZE = 5;

    @Value("${spring.application.name}")
    String appName;

    private void initStack() {
       if(stackDao == null) {
           stackDao = new StackDao(DEFAULT_STACK_SIZE);
       }
    }

    @RequestMapping("/")
    public String index() {
//        model.addAttribute("id", stackSize);
        return "index";
    }

    @PostMapping("/setStackSize")
    public String setStackSize(@RequestParam(value = "capacity", required = true) Integer stackSize, Model model) {
        model.addAttribute("stackSize", stackSize);
        initStack();
        this.stackDao = new StackDao(stackSize);
        return "stackOperations";
    }

    @RequestMapping(value = "/stack", method = RequestMethod.GET)
    public String getStackContents(Model model) {
        Response response = new Response();
        initStack();
        response.setMessage(stackDao.getStackElements());
        model.addAttribute("response", response);
        return "viewStack";
    }

    @RequestMapping(path = "/top", method = RequestMethod.GET)
    public String getTop(Model model) {
        Response response = new Response();
        initStack();
        response.setMessage(stackDao.peek());
        model.addAttribute("response", response);
        return "top";
    }

    @RequestMapping(path = "/pop", method = RequestMethod.POST)
    public String pop(Model model) {
        Response response = new Response();
        initStack();
        response.setMessage(stackDao.pop());
        model.addAttribute("response", response);
        return "pop";
    }

    @PostMapping(value = "/push")
    public String pushInteger(@RequestParam(value = "num", required = true) Integer value, Model model) {
        Response response = new Response();
        System.out.println("Process Form: " + value);
        initStack();
        boolean ret = stackDao.push(value);
        if(!ret) {
            response.setMessage("Successfully inserted " + value);
            model.addAttribute("response", response);
            return "redirect:/stack";
        } else {
            response.setMessage("Stack is Full");
            model.addAttribute("response", response);
            return "stackFull";
        }
    }
}
