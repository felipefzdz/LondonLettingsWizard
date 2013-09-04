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

    public Price(int bedrooms, int minPrice, int maxPrice, int halfPrice) {
        this.bedrooms = bedrooms;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.halfPrice = halfPrice;
    }




}
