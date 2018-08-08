package fi.academy.ravintolapeli.objects;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Mission {//suoritettava tehtävä

    @Id
    private String _id;
    private String title;
    private String imageurl;
    private String story;
    private String borough;
    private String cuisine;
    private String name;


    public Mission() {
    }

    public Mission(String title, String imageurl, String story, String borough, String cuisine, String name) {
        this.title = title;
        this.imageurl = imageurl;
        this.story = story;
        this.borough = borough;
        this.cuisine = cuisine;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "title: '" + title + '\'' +
                ", imageurl: '" + imageurl + '\'' +
                ", story: '" + story + '\'' +
                ", borough: '" + borough + '\'' +
                ", cuisine: '" + cuisine + '\'' +
                ", name: '" + name + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getBorough() {
        return borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

}
