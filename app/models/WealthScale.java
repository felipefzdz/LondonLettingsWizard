package models;

public enum WealthScale {

    OUTOFRANGE(-1000), VERYLOW(1), LOW(0.8), AVERAGE(0.6), HIGH(0.4), VERYHIGH(0.2);

	private static int TIMES_FACTOR = 5;

	private double coefficient;

	private WealthScale(double coefficient){
        this.coefficient = coefficient;
    }
    public static WealthScale getWealthScale(int providedPrice, Price price){
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

    public static Double adjustWealthScale(WealthScale wealthScale, int moneyValue){
		if (wealthScale.equals(OUTOFRANGE)){
			return wealthScale.getCoefficient();
		}
		return moneyValue * wealthScale.getCoefficient() * TIMES_FACTOR;

    }

    public double getCoefficient() {
        return coefficient;
    }


}
