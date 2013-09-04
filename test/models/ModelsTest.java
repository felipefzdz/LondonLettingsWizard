package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebean.config.dbplatform.MySqlPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import org.junit.*;
import static org.junit.Assert.*;

import play.libs.Yaml;
import play.test.WithApplication;
import play.test.FakeApplication;

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
public class ModelsTest extends WithApplication {

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
    public void createAndRetrieveUser() {
        new User("bob@gmail.com", "Bob", "secret").save();
        User bob = User.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }

    @Test
    public void tryAuthenticateUser() {
        new User("bob@gmail.com", "Bob", "secret").save();

        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }

    @Test
    public void testCountInserts() {
        insertInitialData();

        // Count things
        assertEquals(4, User.find.findRowCount());
        assertEquals(3, Area.find.findRowCount());
        assertEquals(9, Price.find.findRowCount());
    }

    @Test
    public void testAreasWithPriceInRange(){
        insertInitialData();
        assertEquals(3, Area.findAreasInRange(200,1).size());
        assertEquals(0, Area.findAreasInRange(200,4).size());
    }



    private void insertInitialData() {
        Map<String,List<Object>> all = (Map<String,List<Object>>) Yaml.load("initial-data.yml");
        Ebean.save(all.get("users"));
        Ebean.save(all.get("areas"));
    }
}
