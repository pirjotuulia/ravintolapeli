package fi.academy.ravintolapeli.controllers;

import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.repositories.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DesignController {
    private MissionRepository repo;

    public DesignController(@Autowired MissionRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/missiondesigner", method = RequestMethod.GET)
    public String missionDesign(Model model) {
        Mission mission = new Mission();
        model.addAttribute("emptyDesign", mission);
        return "missiondesigner";
    }

    @RequestMapping(value = "/addMission", method = RequestMethod.POST)
    public String addMission(@RequestParam String title, String imageurl, String story, String borough, String cuisine, String keyword, Model model) {
        Mission addable = new Mission(title, imageurl, story, borough, cuisine, keyword);
        Mission added = repo.save(addable);
        if (added != null) {
            model.addAttribute("success", true);
            model.addAttribute("added", added);
        } else {
            model.addAttribute("success", false);
            model.addAttribute("error", "Mission design could not be added to mission database.");
            model.addAttribute("addable", addable);
        }
        return "missiondesigner";
    }
}
