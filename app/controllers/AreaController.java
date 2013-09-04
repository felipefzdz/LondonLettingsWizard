package controllers;

import models.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.areaPriceSearch;
import views.html.areaTransportSearch;
import views.html.areaShow;

import java.util.List;

import static play.data.Form.form;

public class AreaController extends Controller {

    static Form<Area> areaForm = form(Area.class);

    static Form<FilterPrice> filterPriceForm = form(FilterPrice.class);


    public static Result areaPriceSearch(){
        return ok(
                areaPriceSearch.render(filterPriceForm)
        );
    }

    public static Result areaPriceFiltered() {
        Form<FilterPrice> filledForm = filterPriceForm.bindFromRequest();
        Integer price = Integer.valueOf(filledForm.data().get("price"));
        Integer bedrooms = Integer.valueOf(filledForm.data().get("bedrooms"));
        List<Area> areas = Area.findAreasPriceInRange(price, bedrooms);
        addAreasToCache(areas);
        return ok(areaTransportSearch.render(areaForm));
    }



    public static Result areaTransportFiltered() {
        Form<FilterPrice> filledForm = filterPriceForm.bindFromRequest();
        Integer rateTransport = Integer.valueOf(filledForm.data().get("rateTransport"));
        List<Area> areas = getAreasFromCache();
        List<Area> areasFiltered = Area.filterByTransportRate(areas, rateTransport);
        return ok(
                areaShow.render(areasFiltered)
        );
    }

    public static Result areaShow() {
        List<Area> areas = getAreasFromCache();
        return ok(
                areaShow.render(areas)
        );
    }

    private static void addAreasToCache(List<Area> areas) {
        // Generate a unique ID
        String uuid=session("uuid");
        if(uuid==null) {
            uuid=java.util.UUID.randomUUID().toString();
            session("uuid", uuid);
        }
        Cache.set(uuid+"areas", areas );
    }

    private static List<Area> getAreasFromCache() {
        String uuid=session("uuid");
        return (List<Area>) Cache.get(uuid + "areas");
    }


}
