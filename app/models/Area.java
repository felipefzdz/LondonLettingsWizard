package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 30/08/13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Area extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    public Integer minPrice;

    public Integer maxPrice;

    public Integer rateTransport;

    public static Model.Finder<Long,Area> find = new Model.Finder<Long, Area>(
            Long.class, Area.class
    );

    public static List<Area> all() {
        return find.all();
    }

    public static void create(Area area) {
        area.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }


}
