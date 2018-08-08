package fi.academy.ravintolapeli.controllers;

import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class MissionController {//rest-rajapinta pelimissioihin
    private MissionRepository repo;

    public MissionController(@Autowired MissionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/missions/{word}")//haetaan hakusanalla Story-kentästä, jos se sisältää sanan tai sanan osan
    public List<Mission> listMissions(@PathVariable String word) {
        List<Mission> listOfMissions = repo.findAllByStoryIsContaining(word);
        if (listOfMissions.isEmpty()) { // jos hakusanalla ei löytynyt mitään, haetaan kaikista
            listOfMissions = repo.findAll();
        }
        if (listOfMissions.size() > 5) { //jos listalla on enemmän kuin 5 missiota
            Collections.shuffle(listOfMissions); //sekoitetaan missiot
            listOfMissions = listOfMissions.stream().limit(5).collect(Collectors.toList());// otetaan listalle vain viisi ensimmäistä
        }
        return listOfMissions;
    }

    @GetMapping("/missions")
    public List<Mission> listAllMissions() {
        List<Mission> listOfMissions = repo.findAll();
        if (listOfMissions.size() > 5) {
            Collections.shuffle(listOfMissions);
            listOfMissions = listOfMissions.stream().limit(5).collect(Collectors.toList());
        }
        return listOfMissions;
    }

    @PostMapping("/missions")
    public Mission saveMission(@RequestBody Mission mission) {
        if (mission.getKeyword() == null || mission.getKeyword().isEmpty()) {//jos keyword on tyhjä tai puuttuu, lisätään oliolle "all"-keyword
            mission.setKeyword("all");
        }
        mission = repo.save(mission);
        return mission;
    }

    @PutMapping("/missions/{id}")
    public Mission changeMission(@PathVariable String id, @RequestBody Mission changedMission) {
        if (!repo.existsById(id)) {//jos id:llä ei löydy dokumenttia, lisätään keywordiksi "all", jos se oli tyhjä tai puuttui
            if (changedMission.getKeyword().isEmpty()) {
                changedMission.setKeyword("all");
            }
        } else {
            changedMission.set_id(id); //jos id:llä löytyi dokumentti, lisätään oliolle id ennen tietokantaan tallettamista, jotta tietokanta tunnistaa olion samaksi kuin dokumentti
        }
        changedMission = repo.save(changedMission);
        return changedMission;
    }

    @DeleteMapping("/missions/{id}")
    public boolean deleteMission(@PathVariable String id) {
        if (repo.existsById(id)) {// jos id:llä löytyy dokumentti
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

