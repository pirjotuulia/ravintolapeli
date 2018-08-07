package fi.academy.ravintolapeli.objects.restaurant;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Address {
    private String building;
    private List<String> coord;
    private String street;
    private String zipcode;

    public Address() {
    }

    public Address(String building, List<String> coord, String street, String zipcode) {
        this.building = building;
        this.coord = coord;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "{" +
                "building: '" + building + '\'' +
                ", coord: " + coord +
                ", street: '" + street + '\'' +
                ", zipcode: '" + zipcode + '\'' +
                '}';
    }

    public String getLatitude() {
        return this.coord.get(1);
    }

    public String getLongitude() {
        return this.coord.get(0);
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<String> getCoord() {
        return coord;
    }

    public void setCoord(List<String> coord) {
        this.coord = coord;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
