package api.xeliox.colorapi;

public class VersionCheck {
    public static boolean serverIsNew() {
        String version = org.bukkit.Bukkit.getVersion();
        String[] parts = version.split("MC: ");
        if (parts.length < 2) {
            return false;
        }
        String mcVersion = parts[1].replace(")", "").trim();
        String[] versionNumbers = mcVersion.split("\\.");
        if (versionNumbers.length < 2) {
            return false;
        }
        try {
            int major = Integer.parseInt(versionNumbers[0]);
            int minor = Integer.parseInt(versionNumbers[1]);
            return (major > 1) || (major == 1 && minor >= 16);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
