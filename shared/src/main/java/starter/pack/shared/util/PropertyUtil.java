package starter.pack.shared.util;

import starter.pack.shared.model.EnvironmentConfigurations;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertyUtil {
    private static final Path windowsPath = Paths.get("C:\\starter-pack\\config.json");
    private static final Path unixPath = Paths.get("/etc/starter-pack/config.json");

    public static EnvironmentConfigurations getEnvironmentConfigurationsByOS() {
        if (OSUtil.isWindows()) {
            return getEnvironmentConfigurations(windowsPath);
        } else if (OSUtil.isUnix()) {
            return getEnvironmentConfigurations(unixPath);
        } else {
            return null;
        }
    }

    public static EnvironmentConfigurations getEnvironmentConfigurations(Path path) {
        String fileContent = OSUtil.getFileContext(path);
        EnvironmentConfigurations environmentConfigurations = JsonUtil.deSerialize(fileContent, EnvironmentConfigurations.class);
        return environmentConfigurations;
    }
}
