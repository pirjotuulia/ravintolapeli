package fi.academy.ravintolapeli.controllers;

import com.mongodb.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameController {
    MongoClient client = new MongoClient();
    DB db = client.getDB("test"); // TODO: Tämä on deprekoitu, mutta sain sen helpoiten toimimaan; vaihdetaan jos ehditään...
    DBCollection coll = db.getCollection("restaurants");

    @GetMapping("/page")
    public String getPage(Model model) {
        return "Game";
    }
}