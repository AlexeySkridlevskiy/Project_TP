package spring_server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import database.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/alcohol")
class AlcoholController {
    HibernateRequest hibernateRequest = new HibernateRequest();

    @GetMapping("/type/{typeID}")
    public List<Alcohol> getAlcoholByType(@PathVariable long typeID) {
        List<Alcohol> result = hibernateRequest.getNameAlcoholByType(typeID);

        if (!result.isEmpty()) {
            String type = hibernateRequest.getTypeByID(typeID);
            for (var a : result) {
                a.setRating(hibernateRequest.getRating(a.getID()));
                a.setType(type);
            }
        }
        result.sort(new Comparator<Alcohol>() {
            public int compare(Alcohol a1, Alcohol a2) {
                if (a1.getRating() == a2.getRating()) {
                    return 0;
                }
                return a2.getRating() > a1.getRating() ? 1 : -1;
            }
        });
        return result;
    }

    @GetMapping("/{ID}")
    public Alcohol getAlcoholByID(@PathVariable long ID) {
        Alcohol result = hibernateRequest.getAlcoholByID(ID);
        result.setRating(hibernateRequest.getRating(result.getID()));
        result.setType(hibernateRequest.getTypeByID(result.getTypeID()));
        return result;
    }

    @GetMapping("/search/{alcoholName}")
    public List<Alcohol> getAlcoholByName(@PathVariable String alcoholName) {
        String name = URLDecoder.decode(alcoholName, StandardCharsets.UTF_8);
        List<Alcohol> result = hibernateRequest.getSearchAlcoholByName(name);
        if (!result.isEmpty()) {
            for (var a : result) {
                a.setRating(hibernateRequest.getRating(a.getID()));
                a.setType(hibernateRequest.getTypeByID(a.getTypeID()));
            }
        }
        return result;
    }
}