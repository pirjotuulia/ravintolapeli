package fi.academy.ravintolapeli.controllers;

import com.mongodb.*;
import fi.academy.ravintolapeli.objects.GameStats;
import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.objects.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class GameController {
    private GameStats stats;
    private RestTemplate restTemplate;

    public GameController(@Autowired GameStats stats) {
        this.stats = stats;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/")
    public String game(Model model) {
        if (this.stats.getHand().size()==0) {//jos kyseessä on ensimmäinen vuoro, haetaan uudet missionit
            ResponseEntity<List<Mission>> response = restTemplate.exchange(
                    "http://localhost:8080/missions/",
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Mission>>() {
                    });
            this.stats.setHand(response.getBody());
        }
        model.addAttribute("gamestats", this.stats);
        return "Game";
    }


    @GetMapping("/play")
    public String play(@RequestBody int index, Restaurant restaurant, double distance, int time, Model model) {
        this.stats = this.stats.makeMove(index, restaurant, distance, time); //päivitetään statsit
        model.addAttribute("gamestats", this.stats);
        if (this.stats.getHand().isEmpty()||this.stats.getHealth()<=0||this.stats.getMoney()<=0) { //jos peli loppuu joko terveyden, rahojen tai korttien loppumisen vuoksi
            this.stats.gameOver();
        }
        return "redirect:/";
    }
//    MongoClient client = new MongoClient();
//    DB db = client.getDB("test"); // TODO: Tämä on deprekoitu, mutta sain sen helpoiten toimimaan; vaihdetaan jos ehditään...
//    DBCollection coll = db.getCollection("restaurants");
//
//    @GetMapping("/page")
//    public String getPage(Model model) {
////        List list = coll.find(BasicDBObject.parse("{ $and: [ " +
////                "{ grades: { $not: { $size: 0 } } }, " +
////                "{ grades: { $not: { $size: 1 } } }, " +
////                "{ name: { $regex: '" + ((name.equals("all")) ? "(?s).*" : name) + "' }}, " +
////                "{ borough: { $regex: '" + ((borough.equals("all")) ? "(?s).*" : borough) + "' }}, " +
////                "{ cuisine: { $regex: '" + ((cuisine.equals("all")) ? "(?s).*" : cuisine) + "' }} ] }"))
////                .sort(BasicDBObject.parse("{name:1}"))
////                .limit(10)
////                .toArray();
////        model.addAttribute("restaurants",list);
//        return "Game";
//    }


}