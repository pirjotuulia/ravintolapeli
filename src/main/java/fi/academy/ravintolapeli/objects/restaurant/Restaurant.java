package fi.academy.ravintolapeli.objects.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Restaurant {//ravintolaolio, joka vastaa databasen ravintolaa
    @JsonIgnore
    @Id
    private String _id;
    private Address address;
    private String borough;
    private String cuisine;
    private List<Grade> grades;
    private String name;
    private int restaurant_id;

    public Restaurant() {
    }

    public Restaurant(Address address, String borough, String cuisine, List<Grade> grades, String name, int restaurant_id) {
        this.address = address;
        this.borough = borough;
        this.cuisine = cuisine;
        this.grades = grades;
        this.name = name;
        this.restaurant_id = restaurant_id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "_id='" + _id + '\'' +
                ", address=" + address +
                ", borough='" + borough + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", grades=" + grades +
                ", name='" + name + '\'' +
                ", restaurant_id=" + restaurant_id +
                '}';
    }

    public String getLatitude() {
        return this.address.getLatitude();
    }

    public String getLongitude() {
        return this.address.getLongitude();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade newGrade) {
        this.grades.add(newGrade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
