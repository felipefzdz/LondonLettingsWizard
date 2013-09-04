package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.areaSearch;
import views.html.areaShow;

import java.util.ArrayList;
import java.util.Iterator;
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

    public static Result areasFiltered() {
        Form<FilterPrice> filledForm = filterPriceForm.bindFromRequest();
        Integer price = Integer.valueOf(filledForm.data().get("price"));
        Integer bedrooms = Integer.valueOf(filledForm.data().get("bedrooms"));
        List<Area> areas = Area.findAreasInRange(price, bedrooms);
        return ok(
                areaShow.render(areas, areaForm)
        );
    }


    public static Result areasSearch(){
        return ok(
                areaSearch.render(filterPriceForm)
        );
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




}
