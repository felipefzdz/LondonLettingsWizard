package models;

import org.junit.Test;
import static org.junit.Assert.*;


public class WealthScaleTest extends BaseTest {

    @Test
    public void testOutOfRange(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.OUTOFRANGE, WealthScale.getWealthScale(1500, price));
    }

    @Test
    public void testVeryLow(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.VERYLOW, WealthScale.getWealthScale(1700, price));
    }

    @Test
    public void testLow(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.LOW, WealthScale.getWealthScale(1900, price));
    }

    @Test
    public void testAverage(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.AVERAGE, WealthScale.getWealthScale(2200, price));
    }

    @Test
    public void testHigh(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.HIGH, WealthScale.getWealthScale(2500, price));
    }

    @Test
    public void testVeryHigh(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.VERYHIGH, WealthScale.getWealthScale(2900, price));
    }

    @Test
    public void testVeryHighOutOfRange(){
        insertInitialData();
        Price price = Price.find.byId(1L);
        assertEquals(WealthScale.VERYHIGH, WealthScale.getWealthScale(3900, price));
    }

    @Test
    public void testAdjustScaleOutOfRange(){
        insertInitialData();
        assertEquals(new Double(-1000), WealthScale.adjustWealthScale(WealthScale.OUTOFRANGE, 5));
    }

    @Test
    public void testAdjustScaleVeryLow(){
        insertInitialData();
        assertEquals(new Double(25), WealthScale.adjustWealthScale(WealthScale.VERYLOW, 5));
    }

    @Test
    public void testAdjustScaleLow(){
        insertInitialData();
        assertEquals(new Double(20), WealthScale.adjustWealthScale(WealthScale.LOW, 5));
    }

    @Test
    public void testAdjustScaleAverage(){
        insertInitialData();
        assertEquals(new Double(15), WealthScale.adjustWealthScale(WealthScale.AVERAGE, 5));
    }

    @Test
    public void testAdjustScaleHigh(){
        insertInitialData();
        assertEquals(new Double(10), WealthScale.adjustWealthScale(WealthScale.HIGH, 5));
    }

    @Test
    public void testAdjustScaleVeryHigh(){
        insertInitialData();
        assertEquals(new Double(5), WealthScale.adjustWealthScale(WealthScale.VERYHIGH, 5));
    }



}
