package starter.pack.shared.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class OSUtil {
    private static String OS = null;

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }

        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix() {
        return getOsName().startsWith("gg?");
    }

    public static String getFileContext(Path path) {
        try {
            return Files.readAllLines(path).stream().collect(Collectors.joining());
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
