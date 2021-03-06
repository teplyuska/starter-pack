package starter.pack.shared.util;

import org.junit.Assert;
import org.junit.Test;
import starter.pack.shared.model.EnvironmentConfigurations;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertyUtilTest {
    @Test
    public void getEnvironmentConfigurationsTest() throws URISyntaxException {
        URL resource = PropertyUtilTest.class.getClassLoader().getResource("config.json");
        Path path = Paths.get(resource.toURI());
        EnvironmentConfigurations environmentConfigurations = PropertyUtil.getEnvironmentConfigurations(path);

        Assert.assertEquals(environmentConfigurations.getDatabaseIp(), "iamdatabaseip");
        Assert.assertEquals(environmentConfigurations.getDatabaseName(), "iamdatabasename");
        Assert.assertEquals(environmentConfigurations.getDatabaseUser(), "iamdatabaseuser");
        Assert.assertEquals(environmentConfigurations.getDatabasePassword(), "iamdatabasepassword");
    }
}