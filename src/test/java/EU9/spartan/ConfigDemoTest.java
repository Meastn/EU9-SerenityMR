package EU9.spartan;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;
import utilities.ConfigurationReader;

public class ConfigDemoTest {

    @Test
    public void test1(){
        System.out.println(ConfigReader.getProperty("serenity.project.name"));
    }
}
