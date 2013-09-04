package models;

import com.avaje.ebean.QueryIterator;
import org.omg.CORBA.PUBLIC_MEMBER;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;


@Entity
public class Area extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    public Integer rateTransport;

    public WealthScale wealthScale;

    @Column(columnDefinition = "TEXT")
    public String description;

    public static Model.Finder<Long,Area> find = new Model.Finder<Long, Area>(
            Long.class, Area.class
    );

    public static Model.Finder<Long,Price> findPrice = new Model.Finder<Long, Price>(
            Long.class, Price.class
    );

    public Area(Long id, String name, Integer rateTransport) {
        this.id = id;
        this.name = name;
        this.rateTransport = rateTransport;
    }

    public static List<Area> all() {
        return find.all();
    }

    public static void create(Area area) {
        area.save();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

    public static List<Area> findAreasPriceInRange(int providedPrice, int bedrooms) {
        QueryIterator<Price> it = findPrice.where()
                .eq("bedrooms", bedrooms)
                .betweenProperties("minPrice", "maxPrice", providedPrice)
                .findIterate();

        Set<Area> areas = new HashSet<Area>();
        while(it.hasNext()){
            Price price = it.next();
            Area area = price.area;
            area.setWealthScale(WealthScale.calculateWealthScale(providedPrice, price));
            areas.add(area);
        }
        return new ArrayList<Area>(areas);
    }


    public static List<Area> filterByTransportRate(List<Area> areas, Integer rateTransport) {
        List<Area> filteredAreas = new ArrayList<Area>();
        for(Area area: areas){
            if (area.rateTransport >= rateTransport){
                filteredAreas.add(area);
            }
        }
        return filteredAreas;
    }


    public void setWealthScale(WealthScale wealthScale) {
        this.wealthScale = wealthScale;
    }



}
