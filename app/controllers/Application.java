package controllers;

import models.Area;
import models.User;
import play.mvc.*;
import play.data.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {


    static Form<Area> areaForm = form(Area.class);

    public static Result index() {
        return redirect(routes.Application.areas());
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
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


    public static class Login {

        public String email;
        public String password;


        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }


}
