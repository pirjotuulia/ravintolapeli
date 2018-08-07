package fi.academy.ravintolapeli.controllers;

import com.mongodb.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RestaurantController {//rest-rajapinta ravintoloiden tietoihin
    MongoClient client = new MongoClient();
    DB db = client.getDB("test"); // TODO: Tämä on deprekoitu, mutta sain sen helpoiten toimimaan; vaihdetaan jos ehditään...
    DBCollection coll = db.getCollection("restaurants");

    @GetMapping("/list/{borough}/{cuisine}/{name}")
    public List<DBObject> listRestaurants (@PathVariable String borough, @PathVariable String cuisine, @PathVariable String name) {
        return coll.find(BasicDBObject.parse("{ $and: [ " +
                        "{ name: { $regex: '"+((name.equals("all")) ? "(?s).*" : name)+"' }}, " +
                        "{ borough: { $regex: '"+((borough.equals("all")) ? "(?s).*" : borough)+"' }}, " +
                        "{ cuisine: { $regex: '"+((cuisine.equals("all")) ? "(?s).*" : cuisine)+"' }} ] }"))
                .sort(BasicDBObject.parse("{name:1}"))
                .limit(10)
                .toArray();
    }
}
