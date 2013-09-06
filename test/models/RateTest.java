package models;


import org.junit.Test;

import static org.junit.Assert.*;

public class RateTest extends BaseTest {

    @Test
    public void testOutOfRange(){
        insertInitialData();
        AreaFilter areaFilter = new AreaFilter(1500, 3, 5, 6, 7, 8);
        assertEquals(new Double(-994.0), Rate.calculateEndRate(Area.find.byId(1L), areaFilter));
    }

    @Test
    public void testInsideRange(){
        insertInitialData();
        AreaFilter areaFilter = new AreaFilter(2200, 3, 5, 6, 7, 8);
        assertEquals(new Double(30.0), Rate.calculateEndRate(Area.find.byId(1L), areaFilter));
    }



}
