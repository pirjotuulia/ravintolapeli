package fi.academy.ravintolapeli.objects;

import fi.academy.ravintolapeli.objects.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class GameStats {//pelitilanneolio
    private int health;
    private double money;
    private String foodcriticName;
    private List<Mission> hand;
    private List<Mission> playedMissions;
    private boolean missionMode;
    private List<LastMove> moves;

    public GameStats() {
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
        return "{" +
                "health: " + health +
                ", money: " + money +
                ", foodcriticName: '" + foodcriticName + '\'' +
                ", hand: " + hand +
                ", playedMissions: " + playedMissions +
                '}';
    }

    public void makeMove(int index, Restaurant restaurant, double distance, int time) {
        playCard(index);
        int restaurantScore = restaurant.getGrades().get(0).getScore();
        LastMove move = new LastMove(restaurantScore, restaurant.getLongitude(), restaurant.getLatitude(), time, distance, false);
        useHealth(restaurantScore);
        useMoney(move.getUsedMoney());
        this.moves.add(move);
        setMissionMode(false);
    }

    public int useHealth(int used) {
        this.health -= used;
        return this.health;
    }

    public double useMoney(double used) {
        this.money -= used;
        return this.money;
    }

    public void playCard(int index) {
        Mission playedCard = this.hand.get(index);
        this.hand.remove(index);
        this.playedMissions.add(playedCard);
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
