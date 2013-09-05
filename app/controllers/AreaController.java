package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.areaPriceSearch;
import views.html.areaRateSearch;
import views.html.areaShow;

import java.util.*;

import static play.data.Form.form;

public class AreaController extends Controller {

    public static final int TOP_AREAS = 6;

    static Form<AreaFilter> areaFilterForm = form(AreaFilter.class);

    public static Result areaPriceSearch(){
        return ok(
                areaPriceSearch.render(areaFilterForm)
        );
    }

    public static Result areaPriceFiltered() {
        Form<AreaFilter> filledForm = areaFilterForm.bindFromRequest();
        session().put("price", filledForm.data().get("price"));
        session().put("bedrooms", filledForm.data().get("bedrooms"));
        return ok(areaRateSearch.render(areaFilterForm));
    }

    public static Result areaRateFiltered() {
        Form<AreaFilter> filledForm = areaFilterForm.bindFromRequest();
        List<Area> areas = Area.all();
        TreeMap<Integer, Area> mapArea = new TreeMap<Integer, Area>(new AreaComparator());
        for(Area area: areas){
            Integer endRate = calculateEndRate(area, filledForm);
            mapArea.put(endRate, area);
        }
        return ok(
                areaShow.render(mapArea, getTopAreas(mapArea), filledForm.data())
        );
    }

    private static Integer calculateEndRate(Area area, Form<AreaFilter> filledForm) {
        Integer rateTransport = Integer.valueOf(filledForm.data().get("rateTransport"));
        Integer greenSpaces = Integer.valueOf(filledForm.data().get("greenSpaces"));
        Integer nightLife = Integer.valueOf(filledForm.data().get("nightLife"));
        Integer moneyValue = Integer.valueOf(filledForm.data().get("moneyValue"));
        Integer price = Integer.valueOf(session().get("price"));
        Integer bedrooms = Integer.valueOf(session().get("bedrooms"));
        Rate rate = area.rate;
        int rateTransportEndRate = rate.rateTransport - rateTransport;
        int greenSpacesEndRate = rate.greenSpaces - greenSpaces;
        int nightLifeEndRate = rate.nightLife - nightLife;
        int moneyValueEndRate = rate.moneyValue - moneyValue;
        int composeEndRate = rateTransportEndRate + greenSpacesEndRate + nightLifeEndRate + moneyValueEndRate;
        WealthScale wealthScale = WealthScale.calculateWealthScale(price, Price.findByAreaAndBedrooms(area, bedrooms));
        return composeEndRate *  wealthScale.getRateWealthScale();
    }

    private static List<Area> getTopAreas(TreeMap<Integer, Area> mapArea) {
        List<Area> topAreas = new ArrayList<Area>();
        Iterator<Map.Entry<Integer, Area>> it = mapArea.entrySet().iterator();
        int i= 0;
        while (it.hasNext() && i < TOP_AREAS) {
            topAreas.add(it.next().getValue());
            i++;
        }
        return topAreas;
    }

    private static class AreaComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            int i = o2.compareTo(o1);
            return i == 0 ? -1 : i;
        }
    }

}
