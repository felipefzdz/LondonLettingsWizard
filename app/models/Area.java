package models;

import play.data.validation.Constraints;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 30/08/13
 * Time: 16:08
 * To change this template use File | Settings | File Templates.
 */
public class Area {

    public Long id;

    @Constraints.Required
    public String name;

    public Integer minPrice;

    public Integer maxPrice;

    public Integer rateTransport;

    public static List<Area> all() {
        return new ArrayList<Area>();
    }

    public static void create(Area area) {
    }

    public static void delete(Long id) {
    }
}
