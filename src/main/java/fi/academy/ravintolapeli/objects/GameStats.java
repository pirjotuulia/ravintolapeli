package fi.academy.ravintolapeli.objects;

import java.util.List;

public class GameStats {//pelitilanneolio
    private int health;
    private int money;
    private String foodcriticName;
    private List<Mission> hand;
    private List<Mission> playedMissions;

    public GameStats() {
    }

    public GameStats(int health, int money, String foodcriticName, List<Mission> hand, List<Mission> playedMissions) {
        this.health = health;
        this.money = money;
        this.foodcriticName = foodcriticName;
        this.hand = hand;
        this.playedMissions = playedMissions;
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

    public int useHealth(int used) {
        this.health-= used;
        return this.health;
    }

    public int useMoney(int used) {
        this.money-= used;
        return this.money;
    }

    public void playCard(int index) {
        Mission playedCard = this.hand.get(index);
        this.hand.remove(index);
        this.playedMissions.add(playedCard);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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
