package fi.academy.ravintolapeli.controllers;

import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MissionController {//rest-rajapinta pelimissioihin
    private MissionRepository repo;

    public MissionController(@Autowired MissionRepository repo) {
        this.repo = repo;
    }

//    MongoClient client = new MongoClient();
//    DB db = client.getDB("test"); // TODO: Tämä on deprekoitu, mutta sain sen helpoiten toimimaan; vaihdetaan jos ehditään...
//    DBCollection coll = db.getCollection("missions");

    @GetMapping("/missions")
    public List<Mission> listMissions(String word) {
        List<Mission> listOfMissions = repo.findAllByStoryIsContaining(word);
        return listOfMissions;
    }
}

