package api.xeliox.colorapi;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ColorAPI {

    private static final Pattern HEX_PATTERN = Pattern.compile("#([a-fA-F0-9]{6})|&?#([A-Fa-f0-9]{6})");


    private ColorAPI() {
        // Utility class
    }

    public static String translate(String message) {
        if (message == null) return null;

        if (VersionCheck.serverIsNew()) {
            message = replaceHex(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private static @NotNull String replaceHex(String input) {
        Matcher matcher = HEX_PATTERN.matcher(input);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            String mcColor = toMinecraftHex(hex);
            matcher.appendReplacement(buffer, mcColor);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static @NotNull String toMinecraftHex(@NotNull String hex) {
        StringBuilder out = new StringBuilder("§x");
        for (char c : hex.toCharArray()) {
            out.append('§').append(c);
        }
        return out.toString();
    }

    public static List<String> translate(List<String> lore) {
        if (lore == null) return null;

        return lore.stream()
                .map(ColorAPI::translate)
                .collect(Collectors.toList());
    }
}