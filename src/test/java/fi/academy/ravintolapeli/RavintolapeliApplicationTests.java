package fi.academy.ravintolapeli;

import fi.academy.ravintolapeli.objects.GameStats;
import fi.academy.ravintolapeli.objects.Mission;
import fi.academy.ravintolapeli.objects.restaurant.Restaurant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RavintolapeliApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GameStats stats;

    @Test
    public void getMissions() {
        assertThat(stats.getHand().size(), is(0));
        //haetaan uudet missionit
        ResponseEntity<List<Mission>> response = restTemplate.exchange(
                "http://localhost:8080/missions/",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Mission>>() {
                });
        stats.setHand(response.getBody());
        assertThat(stats.getHand().size(), is(5));
        assertThat(stats.getHand().get(0).getTitle().isEmpty(), is(false));
    }

    @Test
    public void moveCard() {
        ResponseEntity<List<Mission>> response = restTemplate.exchange(
                "http://localhost:8080/missions/",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Mission>>() {
                });
        stats.setHand(response.getBody());
        int handSizeBefore = stats.getHand().size();
        stats.playCard(0);
        assertThat(stats.getHand().size(), is(handSizeBefore - 1));
    }

    @Test
    public void playTurn() {
        String borough = "Staten Island";
        String cuisine = "all";
        String name = "all";
        ResponseEntity<List<Mission>> response = restTemplate.exchange(
                "http://localhost:8080/missions/",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Mission>>() {
                });
        this.stats.setHand(response.getBody());
        ResponseEntity<List<Restaurant>> restaurantResponse = restTemplate.exchange(
                "http://localhost:8080/list/"+borough+"/"+cuisine+"/"+name,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Restaurant>>() {
                });
        List<Restaurant> restaurantList = restaurantResponse.getBody();
        Collections.shuffle(restaurantList);
        restaurantList = restaurantList.stream().limit(10).collect(Collectors.toList());
        Collections.sort(restaurantList);
        this.stats.setRestaurantList(restaurantList);
        int missionIndex = 0;
        this.stats.playCard(missionIndex);
        this.stats = this.stats.makeMove(0, 4);
        assertThat(stats.getGameover().getDistanceCovered(), is(4.0));
        assertThat(stats.getGameover().getRestaurantsCovered(), is(1));
        assertThat(stats.getHealth(), is(100-restaurantList.get(0).getGrades().get(0).getScore()));
    }

}
