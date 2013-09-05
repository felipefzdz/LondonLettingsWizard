package models;

public class AreaFilter {

    public int price;

    public int bedrooms;

    public int rateTransport;

    public int greenSpaces;

    public int nightLife;

    public int moneyValue;

    public AreaFilter(){

    }

    public AreaFilter(int price, int bedrooms, int rateTransport, int greenSpaces, int nightLife, int moneyValue) {
        this.price = price;
        this.bedrooms = bedrooms;
        this.rateTransport = rateTransport;
        this.greenSpaces = greenSpaces;
        this.nightLife = nightLife;
        this.moneyValue = moneyValue;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public void setRateTransport(int rateTransport) {
        this.rateTransport = rateTransport;
    }

    public void setGreenSpaces(int greenSpaces) {
        this.greenSpaces = greenSpaces;
    }

    public void setNightLife(int nightLife) {
        this.nightLife = nightLife;
    }

    public void setMoneyValue(int moneyValue) {
        this.moneyValue = moneyValue;
    }
}
