package models;

import org.junit.Test;
import utils.AreaComparator;

import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class AreaTest extends BaseTest{

    private TreeMap<Integer,Area> mapArea = new TreeMap<Integer, Area>(new AreaComparator());

    @Test
    public void testTopAreasMoreThanMax(){
        mapArea.put(5, islington);
        mapArea.put(2, clapham);
        mapArea.put(56, clapton);
        mapArea.put(-5, hackney);
        mapArea.put(521, highbury);
        mapArea.put(53, soho);
        mapArea.put(4, brixton);

        List<Area> topAreas = Area.getTopAreas(mapArea);
        assertEquals(Area.TOP_AREAS, topAreas.size());
        assertEquals(highbury.name, topAreas.get(0).name);
        assertEquals(clapton.name, topAreas.get(1).name);
    }

    @Test
    public void testTopAreasLessThanMax(){
        mapArea.put(5, islington);
        mapArea.put(-5, hackney);
        mapArea.put(56, clapton);

        List<Area> topAreas = Area.getTopAreas(mapArea);
        assertEquals(3, topAreas.size());
        assertEquals(clapton.name, topAreas.get(0).name);
        assertEquals(hackney.name, topAreas.get(2).name);
    }

    @Test
    public void testTopAreasEqualRateValue(){
        mapArea.put(5, islington);
        mapArea.put(5, hackney);

        List<Area> topAreas = Area.getTopAreas(mapArea);
        assertEquals(2, topAreas.size());
        assertEquals(islington.name, topAreas.get(0).name);
        assertEquals(hackney.name, topAreas.get(1).name);
    }
}
