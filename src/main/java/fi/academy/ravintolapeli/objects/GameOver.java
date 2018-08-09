package fi.academy.ravintolapeli.objects;

public class GameOver {
    private double moneyLeft;
    private double healthLeft;
    private double distanceCovered;
    private int restaurantsCovered;
    private boolean win;

    public GameOver(){
        this.moneyLeft = moneyLeft;
        this.healthLeft = healthLeft;
        this.distanceCovered = 0;
        this.restaurantsCovered = 0;
        this.win =false;
    }

    public double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }

    public double getHealthLeft() {
        return healthLeft;
    }

    public void setHealthLeft(double healthLeft) {
        this.healthLeft = healthLeft;
    }

    public double getDistanceCovered() {
        return distanceCovered;
    }

    public void addDistanceCovered(double distanceCovered) {
        this.distanceCovered += distanceCovered;
    }

    public int getRestaurantsCovered() {
        return restaurantsCovered;
    }

    public void addRestaurantCovered() {
        this.restaurantsCovered++;
    }

    public boolean isWin() {
        return win;
    }

    public void win() {
        this.win = true;
    }
}
