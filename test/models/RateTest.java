package models;


import org.junit.Test;

import static org.junit.Assert.*;

public class RateTest extends BaseTest {

    @Test
    public void testOutOfRange(){
        insertInitialData();
        AreaFilter areaFilter = new AreaFilter(1500, 3, 5, 6, 7, 8);
        assertEquals(new Integer(-999), Rate.calculateEndRate(Area.find.byId(1L), areaFilter));
    }

    @Test
    public void testVeryHighRate(){
        insertInitialData();
        AreaFilter areaFilter = new AreaFilter(3100, 1, 1, 1, 1, 1);
        assertEquals(new Integer(-999), Rate.calculateEndRate(Area.find.byId(1L), areaFilter));
    }

}
