package controllers;

import models.Area;
import models.User;
import play.mvc.*;
import play.data.*;

import views.html.*;

import java.util.List;

import static play.data.Form.form;

public class Application extends Controller {


    static Form<Area> areaForm = form(Area.class);

    public static Result index() {
        return redirect(routes.AreaController.areasSearch());
    }




}
