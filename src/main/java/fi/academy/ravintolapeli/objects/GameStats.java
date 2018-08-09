package fi.academy.ravintolapeli.objects;

import fi.academy.ravintolapeli.objects.restaurant.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameStats {//pelitilanneolio
    private int originalMoney;
    private int originalHealth;
    private int health;
    private double money;
    private String foodcriticName;
    private List<Mission> hand;
    private List<Mission> playedMissions;
    private boolean missionMode;
    private List<LastMove> moves;
    private List<Restaurant> restaurantList;
    private String longitude;
    private String latitude;
    private GameOver gameover;
    private boolean end;

    //asetetaan alkuarvot
    public GameStats() {
        this.originalHealth = 100;
        this.originalMoney = 200;
        this.health = originalHealth;
        this.money = originalMoney;
        this.foodcriticName = "Hugh Fearnley-Whittingstall";
        this.hand = new ArrayList<>();
        this.playedMissions = new ArrayList<>();
        this.missionMode = false;
        this.moves = new ArrayList<>();
        this.restaurantList = restaurantList;
        this.latitude = "40.7676919";
        this.longitude = "-73.98513559999999";
        this.gameover = new GameOver();
        this.end = false;
    }

    public GameStats(int health, int money, String foodcriticName, List<Mission> hand, List<Mission> playedMissions) {
        this.health = health;
        this.money = money;
        this.foodcriticName = foodcriticName;
        this.hand = hand;
        this.playedMissions = playedMissions;
        this.missionMode = false;
        this.moves = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "health=" + health +
                ", money=" + money +
                ", foodcriticName='" + foodcriticName + '\'' +
                ", hand=" + hand +
                ", playedMissions=" + playedMissions +
                ", missionMode=" + missionMode +
                ", moves=" + moves +
                '}';
    }

    //vuoron päätteeksi tehdään tämä setti tietojen päivittämiseksi ja vuoron lisäämiseksi sekä gameover-olion päivittämiseksi
    public GameStats makeMove(int restaurantIndex, double distance) {
        Restaurant restaurant = this.restaurantList.get(restaurantIndex);
        int restaurantScore = restaurant.getGrades().get(0).getScore(); // tallettaa ravintolan scoren
        this.gameover.addDistanceCovered(distance); //lisätään kuljettu matka gameoveriin
        this.gameover.addRestaurantCovered();//lisätään yksi ravintola gameoveriin
        LastMove move = new LastMove(restaurantScore, restaurant.getLongitude(), restaurant.getLatitude(), distance, false, restaurant.getName()); //tekee pelatun vuoron tiedoista LastMove-olion
        useHealth(restaurantScore);// vähentää terveyttä ravintolan scoren verran
        useMoney(move.getUsedMoney()); //käyttää rahaa matkan taksan verran
        setNewCoordinates(restaurant); //asetetaan pelaajalle uusi sijainti
        this.moves.add(move); // lisää pelatun vuoron vuorojen listalle
        setMissionMode(false); // asettaa missionModen kortinvalinta-tilaan
        return this;//palauttaa GameStats-olion (tarvitaanko tätä edes?)
    }

    private void setNewCoordinates(Restaurant restaurant) {
        this.longitude = restaurant.getLongitude();
        this.latitude = restaurant.getLatitude();
    }

    public LastMove getLastMove() {
        if (isLastMove()) {
            return this.moves.get(this.moves.size() - 1);
        }
        return null;
    }

    public boolean isLastMove() {
        if (this.moves.size() > 0) {
            return true;
        }
        return false;
    }

    public void gameOver() {//kun peli on pelattu loppuun
        this.end = true;
        setFinalStats();
//        this.setHand(new ArrayList<>()); //tyhjennetään käsi, riippumatta siitä, oliko se jo tyhjä vai loppuiko peli muusta syystä
//        this.setPlayedMissions(new ArrayList<>()); //tyhjennetään pelatut kortit
//        this.setMissionMode(false); //asetetaan missionMode kortinvalinta-tilaan
//        //LastMoves jätetään ennalleen, eli pelihistoria säilyy
//        this.setHealth(originalHealth);//jos uusi vuoro alkaa täydellä terveydellä
//        this.setMoney(originalMoney);//jos uusi vuoro alkaa täysillä rahoilla
    }

    public void setFinalStats() {
        this.gameover.setHealthLeft(getHealth());
        this.gameover.setMoneyLeft(getMoney());
    }

    public int useHealth(int used) {
        this.health -= used;
        return this.health;
    }

    public double useMoney(double used) {
        this.money -= used;
        return this.money;
    }

    public boolean isGameOver() {
        return end;
    }

    public void playCard(int index) {// siirtää pelatun kortin kädestä pelattuihin, jos missionMode=false
        if (!missionMode) {
            Mission playedCard = this.hand.get(index);
            this.hand.remove(index);
            this.playedMissions.add(playedCard);
            this.setMissionMode(true);
        }
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public boolean isMissionMode() {
        return missionMode;
    }

    public void setMissionMode(boolean missionMode) {
        this.missionMode = missionMode;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getFoodcriticName() {
        return foodcriticName;
    }

    public void setFoodcriticName(String foodcriticName) {
        this.foodcriticName = foodcriticName;
    }

    public List<Mission> getHand() {
        return hand;
    }

    public void setHand(List<Mission> hand) {
        this.hand = hand;
    }

    public List<Mission> getPlayedMissions() {
        return playedMissions;
    }

    public void setPlayedMissions(List<Mission> playedMissions) {
        this.playedMissions = playedMissions;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<LastMove> getMoves() {
        return moves;
    }

    public void setMoves(List<LastMove> moves) {
        this.moves = moves;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public GameOver getGameover() {
        return gameover;
    }

    public void setGameover(GameOver gameover) {
        this.gameover = gameover;
    }

    public void putOnTop(int index) {
        Mission tmp = this.hand.get(index);
        this.hand.remove(index);
        this.hand.add(tmp);

    }

}
