package models;

import org.junit.*;
import static org.junit.Assert.*;


public class ModelsTest extends BaseTest {

    @Test
    public void createAndRetrieveArea() {
        hackney.save();

        Area hackneyDB = Area.find.where().eq("name", "Hackney").findUnique();
        assertNotNull(hackneyDB);
        assertEquals("Hackney", hackneyDB.name);
    }

    @Test
    public void testCountInserts() {
        insertInitialData();

        assertEquals(111, Area.find.findRowCount());
        assertEquals(111, Price.find.findRowCount());
        assertEquals(111, Rate.find.findRowCount());
    }

//    @Test
//    public void testAreasWithPriceInRange(){
//        insertInitialData();
//        assertEquals(3, Area.findAreasPriceInRange(500, 2).size());
//        assertEquals(0, Area.findAreasPriceInRange(200, 4).size());
//    }
//
//    @Test
//    public void testAreasTransportRate(){
//        List<Area> areas = new ArrayList<Area>();
//        areas.add(new Area(1L, "Islington", 5));
//        areas.add(new Area(2L, "Clapham", 3));
//        areas.add(new Area(3L, "Dalston", 9));
//        assertEquals(3, Area.filterByTransportRate(areas, 2).size());
//        assertEquals(2, Area.filterByTransportRate(areas, 4).size());
//        assertEquals(1, Area.filterByTransportRate(areas, 6).size());
//        assertEquals(0, Area.filterByTransportRate(areas, 10).size());
//    }




}
