package spring_server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import database.*;

@RestController
@RequestMapping("/user")
class UserController {
    HibernateRequest hibernateRequest = new HibernateRequest();

    @GetMapping("/{id}")
    public User getUserByID(@PathVariable long id) {
        return hibernateRequest.getUserByID(id);
    }
}
