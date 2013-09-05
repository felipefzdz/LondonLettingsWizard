package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import play.libs.Yaml;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

public class BaseTest extends WithApplication {

    public static FakeApplication app;

    public static Rate rateIslington = new Rate(1L, 6, 7, 2, 3);

    public static Area islington = new Area(1L,"Islington","Desc", rateIslington, "outcode" );

    public static Area clapham = new Area(2L,"Clapham","Desc", new Rate(), "outcode" );

    public static Area dalston = new Area(3L,"Dalston","Desc", new Rate(), "outcode" );

    public static Area soho = new Area(4L,"Soho","Desc", new Rate(), "outcode" );

    public static Area brixton = new Area(5L,"Brixton","Desc", new Rate(), "outcode" );

    public static Area hackney = new Area(6L,"Hackney","Desc", new Rate(), "outcode" );

    public static Area highbury = new Area(7L,"Highbury","Desc", new Rate(), "outcode" );

    public static Area clapton = new Area(8L,"Clapton","Desc", new Rate(), "outcode" );

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

    public void insertInitialData() {
        Map<String,List<Object>> all = (Map<String,List<Object>>) Yaml.load("initial-data.yml");
        Ebean.save(all.get("rates"));
        Ebean.save(all.get("areas"));
        Ebean.save(all.get("prices"));
    }

}
