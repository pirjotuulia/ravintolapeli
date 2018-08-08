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
//        List list = coll.find(BasicDBObject.parse("{ $and: [ " +
//                "{ grades: { $not: { $size: 0 } } }, " +
//                "{ grades: { $not: { $size: 1 } } }, " +
//                "{ name: { $regex: '" + ((name.equals("all")) ? "(?s).*" : name) + "' }}, " +
//                "{ borough: { $regex: '" + ((borough.equals("all")) ? "(?s).*" : borough) + "' }}, " +
//                "{ cuisine: { $regex: '" + ((cuisine.equals("all")) ? "(?s).*" : cuisine) + "' }} ] }"))
//                .sort(BasicDBObject.parse("{name:1}"))
//                .limit(10)
//                .toArray();
//        model.addAttribute("restaurants",list);
        return "Game";
    }
}