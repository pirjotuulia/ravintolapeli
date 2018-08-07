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

    @GetMapping("/missions/{word}")
    public List<Mission> listMissions(@PathVariable String word) {
        List<Mission> listOfMissions = repo.findAllByStoryIsContaining(word);
        if (listOfMissions.isEmpty()) {
            listOfMissions = repo.findAll();
        }
        if (listOfMissions.size() > 5) {
            Collections.shuffle(listOfMissions);
            listOfMissions = listOfMissions.stream().limit(5).collect(Collectors.toList());
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
        if (mission.getKeyword() == null || mission.getKeyword().isEmpty()) {
            mission.setKeyword("all");
        }
        mission = repo.save(mission);
        return mission;
    }

    @PutMapping("/missions/{id}")
    public Mission changeMission(@PathVariable String id, @RequestBody Mission changedMission) {
        if (!repo.existsById(id)) {
            if (changedMission.getKeyword().isEmpty()) {
                changedMission.setKeyword("all");
            }
        } else {
            changedMission.set_id(id);
        }
        changedMission = repo.save(changedMission);
        return changedMission;
    }

    @DeleteMapping("/missions/{id}")
    public boolean deleteMission(@PathVariable String id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}

