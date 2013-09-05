package models;


import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.*;


@Entity
public class Area extends Model{

    public static final int TOP_AREA_THRESHOLD = 20;
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Column(columnDefinition = "TEXT")
    public String description;

    @OneToOne
    public Rate rate;

    public String outcode;

    public static final int TOP_AREAS = 6;

    public static Model.Finder<Long,Area> find = new Model.Finder<Long, Area>(
            Long.class, Area.class
    );

    public Area(){

    }

    public Area(Long id, String name, String description, Rate rate, String outcode) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rate = rate;
        this.outcode = outcode;
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

    public static List<Area> getTopAreas(TreeMap<Integer, Area> mapArea) {
        List<Area> topAreas = new ArrayList<Area>();
        Iterator<Map.Entry<Integer, Area>> it = mapArea.entrySet().iterator();
        int i= 0;
        while (it.hasNext() && i < TOP_AREAS) {
            Map.Entry<Integer, Area> entry = it.next();
            if (entry.getKey() >= TOP_AREA_THRESHOLD){
                topAreas.add(entry.getValue());
                i++;
            }
        }
        return topAreas;
    }
}
