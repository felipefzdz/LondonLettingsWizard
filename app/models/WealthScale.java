package models;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 04/09/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public enum WealthScale {

    OUTOFRANGE(-1000), VERYLOW(10), LOW(12), AVERAGE(14), HIGH(16), VERYHIGH(18);


    private int rateWealthScale;

    private WealthScale(int rateWealthScale){
        this.rateWealthScale = rateWealthScale;
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

    public int getRateWealthScale() {
        return rateWealthScale;
    }
}
