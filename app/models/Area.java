package models;

import com.avaje.ebean.QueryIterator;
import org.omg.CORBA.PUBLIC_MEMBER;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;


@Entity
public class Area extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Column(columnDefinition = "TEXT")
    public String description;

    @OneToOne
    public Rate rate;

    public static Model.Finder<Long,Area> find = new Model.Finder<Long, Area>(
            Long.class, Area.class
    );

    public static Model.Finder<Long,Price> findPrice = new Model.Finder<Long, Price>(
            Long.class, Price.class
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
