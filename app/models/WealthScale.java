package models;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 04/09/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public enum WealthScale {

    OUTOFRANGE(-1000), VERYLOW(1), LOW(0.8), AVERAGE(0.6), HIGH(0.4), VERYHIGH(0.2);


    private double coefficient;

    private WealthScale(double coefficient){
        this.coefficient = coefficient;
    }
    public static WealthScale calculateWealthScale(int providedPrice, Price price){
        if (providedPrice >= price.maxPrice){
            return VERYHIGH;
        }
        if (providedPrice < price.minPrice){
            return OUTOFRANGE;
        }
        if (providedPrice == price.minPrice){
            return VERYLOW;
        }
        if (providedPrice == price.halfPrice){
            return AVERAGE;
        }
        if (providedPrice < price.halfPrice){
            return LOW;
        }
        return HIGH;
    }

    public double getCoefficient() {
        return coefficient;
    }


    public static double getCalculatedWealthScale(WealthScale wealthScale, int moneyValue){
        switch(wealthScale)  {
            case OUTOFRANGE: return OUTOFRANGE.getCoefficient();   // -1000 ~ -1000
            case VERYLOW: return moneyValue * VERYLOW.getCoefficient() * 5;  // 0 ~ 50
            case LOW: return moneyValue * LOW.getCoefficient() * 5; // 0 ~  40
            case AVERAGE: return moneyValue * AVERAGE.getCoefficient() * 5; // 0 ~ 30
            case HIGH: return moneyValue * HIGH.getCoefficient() * 5; // 0 ~ 20
            case VERYHIGH: return moneyValue * VERYHIGH.getCoefficient() * 5; // 0 ~ 10

        }
        return -1000;
    }



}
