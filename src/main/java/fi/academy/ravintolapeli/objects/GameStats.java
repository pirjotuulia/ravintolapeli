package fi.academy.ravintolapeli.objects;

import fi.academy.ravintolapeli.objects.restaurant.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameStats {//pelitilanneolio
    private int health;
    private double money;
    private String foodcriticName;
    private List<Mission> hand;
    private List<Mission> playedMissions;
    private boolean missionMode;
    private List<LastMove> moves;

    //asetetaan alkuarvot
    public GameStats() {
        this.health = 100;
        this.money = 100;
        this.foodcriticName = "Hugh Fearnley-Whittingstall";
        this.hand = new ArrayList<>();
        this.playedMissions = new ArrayList<>();
        this.missionMode = false;
        this.moves = new ArrayList<>();
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

    //vuoron päätteeksi tehdään tämä setti tietojen päivittämiseksi ja vuoron lisäämiseksi
    public GameStats makeMove(int index, Restaurant restaurant, double distance, int time) {
        int restaurantScore = restaurant.getGrades().get(0).getScore(); // tallettaa ravintolan scoren
        LastMove move = new LastMove(restaurantScore, restaurant.getLongitude(), restaurant.getLatitude(), time, distance, false); //tekee pelatun vuoron tiedoista LastMove-olion
        useHealth(restaurantScore);// vähentää terveyttä ravintolan scoren verran
        useMoney(move.getUsedMoney()); //käyttää rahaa matkan taksan verran
        this.moves.add(move); // lisää pelatun vuoron vuorojen listalle
        setMissionMode(false); // asettaa missionModen kortinvalinta-tilaan
        return this;//palauttaa GameStats-olion (tarvitaanko tätä edes?)
    }

    public List<LastMove> getMoves() {
        return moves;
    }

    public void setMoves(List<LastMove> moves) {
        this.moves = moves;
    }

    public void gameOver() {//kun peli on pelattu loppuun
        this.setHand(new ArrayList<>()); //tyhjennetään käsi, riippumatta siitä, oliko se jo tyhjä vai loppuiko peli muusta syystä
        this.setPlayedMissions(new ArrayList<>()); //tyhjennetään pelatut kortit
        this.setMissionMode(false); //asetetaan missionMode kortinvalinta-tilaan
        //LastMoves jätetään ennalleen, eli pelihistoria säilyy
        this.setHealth(100);//jos uusi vuoro alkaa täydellä terveydellä
        this.setMoney(100);//jos uusi vuoro alkaa täysillä rahoilla
    }

    public int useHealth(int used) {
        this.health -= used;
        return this.health;
    }

    public double useMoney(double used) {
        this.money -= used;
        return this.money;
    }

    public void playCard(int index) {// siirtää pelatun kortin kädestä pelattuihin, jos missionMode=false
        if (!missionMode) {
            Mission playedCard = this.hand.get(index);
            this.hand.remove(index);
            this.playedMissions.add(playedCard);
            this.setMissionMode(true);
        }
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


}
