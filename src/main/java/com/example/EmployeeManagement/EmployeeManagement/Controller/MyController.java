package com.example.EmployeeManagement.EmployeeManagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EmployeeManagement")
public class MyController
{
    @GetMapping("/help")
    public String getHelp()
    {
        return "This is just for testing purposes! --- HELLO WORLD ---";
    }
}
