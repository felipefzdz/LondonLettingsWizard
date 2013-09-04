package controllers;

import models.*;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.areaPriceSearch;
import views.html.areaTransportSearch;
import views.html.areaShow;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

public class AreaController extends Controller {


    static Form<Area> areaForm = form(Area.class);

    static Form<FilterPrice> filterPriceForm = form(FilterPrice.class);


    public static Result areas() {
        return ok(
                areaShow.render(Area.all(), areaForm)
        );
    }

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
        List<Area> areasFiltered = filterByTransportRate(areas, rateTransport);
        return ok(
                areaShow.render(areasFiltered, areaForm)
        );
    }

    private static List<Area> getAreasFromCache() {
        String uuid=session("uuid");
        return (List<Area>) Cache.get(uuid + "areas");
    }



    public static Result newArea() {
        Form<Area> filledForm = areaForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    areaShow.render(Area.all(), filledForm)
            );
        } else {
            Area.create(filledForm.get());
            return redirect(routes.AreaController.areas());
        }
    }



    public static Result deleteArea(Long id) {
        Area.delete(id);
        return redirect(routes.AreaController.areas());
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

    private static List<Area> filterByTransportRate(List<Area> areas, Integer rateTransport) {
        List<Area> filteredAreas = new ArrayList<Area>();
        for(Area area: areas){
            if (area.rateTransport >= rateTransport){
                filteredAreas.add(area);
            }
        }
        return filteredAreas;
    }




}
