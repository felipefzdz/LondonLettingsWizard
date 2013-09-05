package models;

import com.avaje.ebean.QueryIterator;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.*;

@Entity
public class Price extends Model{

    @Id
    public Long id;

    public int bedrooms;

    public int minPrice;

    public int halfPrice;

    public int maxPrice;

    @ManyToOne
    public Area area;

    public static Model.Finder<Long,Price> find = new Model.Finder<Long, Price>(
            Long.class, Price.class
    );

    public static Price findByAreaAndBedrooms(Area area, int bedrooms) {
        return find.where()
                .eq("area", area)
                .eq("bedrooms", bedrooms)
                .findUnique();
    }

    public static Price findByAreaIdAndBedrooms(Long areaId, int bedrooms) {
        return find.where()
                .eq("area.id", areaId)
                .eq("bedrooms", bedrooms)
                .findUnique();
    }




}
