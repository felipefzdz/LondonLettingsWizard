package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rate {

    @Id
    public Long id;

    public int rateTransport;

    public int greenSpaces;

    public int nightLife;

    public int moneyValue;

    public static Model.Finder<Long,Rate> find = new Model.Finder<Long, Rate>(
            Long.class, Rate.class
    );

}
