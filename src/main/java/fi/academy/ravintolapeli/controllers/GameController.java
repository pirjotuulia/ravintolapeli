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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        if (this.stats.getPlayedMissions().size()>0) {//jos löytyy pelattu kortti (peli on jo aloitettu)
            Mission last = stats.getPlayedMissions().get(stats.getPlayedMissions().size()-1);
            if (last.getName()=="") last.setName("all");
            System.out.println(last);
            ResponseEntity<List<Restaurant>> restaurantResponse = restTemplate.exchange(
                    "http://localhost:8080/list/"+last.getBorough()+"/"+last.getCuisine()+"/"+last.getName(),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Restaurant>>() {
                    });
            List<Restaurant> restaurantList = restaurantResponse.getBody();
            Collections.shuffle(restaurantList);
            restaurantList = restaurantList.stream().limit(10).collect(Collectors.toList());
            Collections.sort(restaurantList);
            this.stats.setRestaurantList(restaurantList);
        }

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

    @GetMapping("/top/{index}")
    public String putOnTop(@PathVariable int index, Model model) {
        this.stats.putOnTop(index);
        model.addAttribute("gamestats", this.stats);
        return "Game";
    }

    @GetMapping("/card/{index}")
    public String card(@PathVariable int index, Model model) {
        this.stats.playCard(index);
        model.addAttribute("gamestats", this.stats);
        return "Game";
    }

    @GetMapping("/play/{index}/{distance}")
    public String play(@PathVariable int index, @PathVariable double distance, Model model) {
        System.out.println(index+" "+distance);
        this.stats = this.stats.makeMove(index, distance); //päivitetään statsit
        model.addAttribute("gamestats", this.stats);
        if (this.stats.getHand().isEmpty()||this.stats.getHealth()<=0||this.stats.getMoney()<=0) { //jos peli loppuu joko terveyden, rahojen tai korttien loppumisen vuoksi
            this.stats.gameOver();
        }
        return "redirect:/";
    }
}