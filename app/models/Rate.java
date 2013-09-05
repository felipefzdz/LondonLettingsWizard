package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rate {

    @Id
    public Long id;

    public int rateTransport;

    public int greenSpaces;

    public int nightLife;

    public int moneyValue;

    public static Model.Finder<Long,Rate> find = new Model.Finder<Long, Rate>(
            Long.class, Rate.class
    );

    public Rate(){

    }

    public Rate(Long id, int rateTransport, int greenSpaces, int nightLife, int moneyValue) {
        this.id = id;
        this.rateTransport = rateTransport;
        this.greenSpaces = greenSpaces;
        this.nightLife = nightLife;
        this.moneyValue = moneyValue;
    }

    public static Integer calculateEndRate(Area area, AreaFilter areaFilter) {
        int composeEndRate = area.rate.getSumEndRateWithoutWealthScaleFactor(areaFilter);
        WealthScale wealthScale = WealthScale.calculateWealthScale(areaFilter.price, Price.findByAreaIdAndBedrooms(area.id, areaFilter.bedrooms));
        return composeEndRate + wealthScale.getRateWealthScale();
    }

    private int getSumEndRateWithoutWealthScaleFactor(AreaFilter areaFilter){
        return getDifferenceRateTransport(areaFilter.rateTransport) + getDifferenceGreenSpaces(areaFilter.greenSpaces)
                + getDifferenceNightLife(areaFilter.nightLife) + getDifferenceMoneyValue(areaFilter.moneyValue);
    }

    private int getDifferenceRateTransport(int other){
          return rateTransport - other;
    }

    private int getDifferenceGreenSpaces(int other){
        return greenSpaces - other;
    }

    private int getDifferenceNightLife(int other){
        return nightLife - other;
    }

    private int getDifferenceMoneyValue(int other){
        return moneyValue - other;
    }


}
