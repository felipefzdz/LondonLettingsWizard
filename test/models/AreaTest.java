package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import org.junit.*;
import static org.junit.Assert.*;

import play.libs.Yaml;
import play.test.WithApplication;
import play.test.FakeApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import play.test.Helpers;


import static play.test.Helpers.*;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 03/09/13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class AreaTest extends WithApplication {

    public static FakeApplication app;

    @BeforeClass
    public static void startApp() throws IOException {

        app = Helpers.fakeApplication();
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void dropCreateDb() throws IOException {

        String serverName = "default";

        EbeanServer server = Ebean.getServer(serverName);

        ServerConfig config = new ServerConfig();

        DdlGenerator ddl = new DdlGenerator((SpiEbeanServer) server, new H2Platform(), config);

        // Drop
        ddl.runScript(false, ddl.generateDropDdl());

        // Create
        ddl.runScript(false, ddl.generateCreateDdl());
    }



    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }


    @Test
    public void createAndRetrieveArea() {
        new Area(1l,"Islington", new Integer(5)).save();
        Area islington = Area.find.where().eq("name", "Islington").findUnique();
        assertNotNull(islington);
        assertEquals("Islington", islington.name);
    }

    @Test
    public void testCountInserts() {
        insertInitialData();

        // Count things
        assertEquals(3, Area.find.findRowCount());
        assertEquals(9, Price.find.findRowCount());
    }

    @Test
    public void testAreasWithPriceInRange(){
        insertInitialData();
        assertEquals(3, Area.findAreasPriceInRange(500, 2).size());
        assertEquals(0, Area.findAreasPriceInRange(200, 4).size());
    }

    @Test
    public void testAreasTransportRate(){
        List<Area> areas = new ArrayList<Area>();
        areas.add(new Area(1L, "Islington", 5));
        areas.add(new Area(2L, "Clapham", 3));
        areas.add(new Area(3L, "Dalston", 9));
        assertEquals(3, Area.filterByTransportRate(areas, 2).size());
        assertEquals(2, Area.filterByTransportRate(areas, 4).size());
        assertEquals(1, Area.filterByTransportRate(areas, 6).size());
        assertEquals(0, Area.filterByTransportRate(areas, 10).size());
    }



    private void insertInitialData() {
        Map<String,List<Object>> all = (Map<String,List<Object>>) Yaml.load("initial-data.yml");
        Ebean.save(all.get("areas"));
        Ebean.save(all.get("prices"));
    }
}
