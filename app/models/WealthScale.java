package models;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 04/09/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public enum WealthScale {

    VERYLOW, LOW, AVERAGE, HIGH, VERYHIGH;

    public static WealthScale calculateWealthScale(int providedPrice, Price price){
        if (providedPrice == price.halfPrice){
            return AVERAGE;
        }
        if (providedPrice == price.minPrice){
            return VERYLOW;
        }
        if (providedPrice == price.maxPrice){
            return VERYHIGH;
        }
        if (providedPrice < price.halfPrice){
            return LOW;
        }
        return HIGH;
    }
}
