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

    /**
     *
     * @param providedPrice
     * @param price
     * @return
     * throws IllegalArgumentException if the providedPrice is less than the minPrice included in price or is greater than maxPrice included in price.
     */
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
        if (providedPrice > price.halfPrice){
            return HIGH;
        }
        throw new IllegalArgumentException("The provided price should be in the range");
    }
}
