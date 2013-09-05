package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AreaComparator;
import views.html.areaPriceSearch;
import views.html.areaRateSearch;
import views.html.areaShow;

import java.util.*;

import static play.data.Form.form;

public class AreaController extends Controller {

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
            Integer endRate = Rate.calculateEndRate(area, getAreaFilter(filledForm)).intValue();
            mapArea.put(endRate, area);
        }
        return ok(
                areaShow.render(mapArea, Area.getTopAreas(mapArea), filledForm.data())
        );
    }

    private static AreaFilter getAreaFilter(Form<AreaFilter> filledForm) {
        AreaFilter areaFilter = filledForm.get();
        areaFilter.setBedrooms(Integer.valueOf(session().get("bedrooms")));
        areaFilter.setPrice(Integer.valueOf(session().get("price")));
        return areaFilter;
    }

}
