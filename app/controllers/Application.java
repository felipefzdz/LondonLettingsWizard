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
        return TODO;
    }

    public static Result deleteArea(Long id) {
        return TODO;
    }
  
}
