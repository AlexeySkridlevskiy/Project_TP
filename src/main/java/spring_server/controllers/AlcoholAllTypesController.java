package spring_server.controllers;

import database.AlcoholTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import database.*;

import java.util.List;

@RestController
@RequestMapping("/AlcoholAllTypes")
class AlcoholAllTypesController {
    HibernateRequest hibernateRequest = new HibernateRequest();

    @GetMapping("/GetAllTypes")
    public List<AlcoholTypes> getAllAlcoholTypes() {
        return hibernateRequest.getAllAlcoholTypes();
    }
}