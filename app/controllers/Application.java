package controllers;

import models.Area;
import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;

public class Application extends Controller {


    static Form<Area> areaForm = Form.form(Area.class);

    public static Result index() {
        return redirect(routes.Application.areas());
    }

    public static Result areas() {
        return ok(
                views.html.index.render(Area.all(), areaForm)
        );
    }

    public static Result newArea() {
        Form<Area> filledForm = areaForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Area.all(), filledForm)
            );
        } else {
            Area.create(filledForm.get());
            return redirect(routes.Application.areas());
        }
    }

    public static Result deleteArea(Long id) {
        Area.delete(id);
        return redirect(routes.Application.areas());
    }


}
