package models;

import com.avaje.ebean.Ebean;
import org.junit.*;
import static org.junit.Assert.*;

import play.libs.Yaml;
import play.test.WithApplication;

import java.util.List;
import java.util.Map;

import static play.test.Helpers.*;

/**
 * Created with IntelliJ IDEA.
 * User: felipef
 * Date: 03/09/13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class ModelsTest extends WithApplication {

    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }


    @Test
    public void createAndRetrieveArea() {
        new Area(1l,"Islington", new Integer(200), new Integer(300), new Integer(5)).save();
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
    public void fullTest() {
        Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("initial-data.yml");
        Ebean.save(all.get("users"));
        Ebean.save(all.get("areas"));

        // Count things
        assertEquals(4, User.find.findRowCount());
        assertEquals(3, Area.find.findRowCount());


        // Try to authenticate as users
        assertNotNull(User.authenticate("bob@example.com", "secret"));
        assertNotNull(User.authenticate("jane@example.com", "secret"));
        assertNull(User.authenticate("jeff@example.com", "badpassword"));
        assertNull(User.authenticate("tom@example.com", "secret"));

    }
}
