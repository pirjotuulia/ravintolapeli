package fi.academy.ravintolapeli.objects;

public class LastMove {
    private int usedHealth;
    private double usedMoney;
    private String longitude;
    private String latitude;
    private int time;
    private double distanceInMiles;

    public LastMove() {
    }

    public LastMove(int usedHealth, String longitude, String latitude, int time, double distanceInMiles, boolean extraFare) {
        this.usedHealth = usedHealth;
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
        this.distanceInMiles = distanceInMiles;
        travelCosts(extraFare);
    }

    public void travelCosts(boolean extraFare) {
        this.usedMoney = 7 + distanceInMiles * 3.75;
        if (extraFare) {
            this.usedMoney *= 2;
        }
    }

    @Override
    public String toString() {
        return "LastMove{" +
                "usedHealth=" + usedHealth +
                ", usedMoney=" + usedMoney +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", time=" + time +
                ", distanceInMiles=" + distanceInMiles +
                '}';
    }

    public int getUsedHealth() {
        return usedHealth;
    }

    public void setUsedHealth(int usedHealth) {
        this.usedHealth = usedHealth;
    }

    public double getUsedMoney() {
        return usedMoney;
    }

    public void setUsedMoney(double usedMoney) {
        this.usedMoney = usedMoney;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistanceInMiles() {
        return distanceInMiles;
    }

    public void setDistanceInMiles(double distanceInMiles) {
        this.distanceInMiles = distanceInMiles;
    }
}
