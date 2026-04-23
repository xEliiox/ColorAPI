package io.github.xeliiox.colorapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * ColorAPI - A lightweight utility for translating color codes in Minecraft.
 * <p>
 * Supports both legacy formatting codes using '&' and modern HEX colors
 * using the '#RRGGBB' and '&#RRGGBB' formats. Automatically detects server
 * version to ensure compatibility with Minecraft 1.16+.
 * </p>
 *
 * @author xEliiox
 * @version 2.0.0
 */
public final class ColorAPI {

    // Matches both #RRGGBB and &#RRGGBB formats (single capture group for the hex value)
    private static final Pattern HEX_PATTERN = Pattern.compile("&?#([A-Fa-f0-9]{6})");

    // Cache the result of the version check to avoid repeated calls to Bukkit.getVersion()
    private static final boolean IS_HEX_SUPPORTED = checkHexSupport();

    private ColorAPI() {
        // Utility class - no instances allowed
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Translates all color codes in the given string.
     * <ul>
     *     <li>Translates legacy codes using the '&amp;' character (e.g., &amp;a -> green).</li>
     *     <li>Translates HEX codes in '#RRGGBB' format (e.g., #FF5555 -> light red).</li>
     *     <li>Translates HEX codes in '&amp;#RRGGBB' format (e.g., &amp;#FF5555 -> light red).</li>
     * </ul>
     * <p>
     * HEX support is automatically enabled on Minecraft 1.16+ servers.
     * On older versions, HEX codes will be left unchanged.
     * </p>
     *
     * @param message The string to translate. May be null.
     * @return The translated string with colors applied, or null if the input was null.
     */
    public static String translate(String message) {
        if (message == null) {
            return null;
        }

        // 1. Translate HEX colors if supported
        if (IS_HEX_SUPPORTED) {
            message = replaceHexColors(message);
        }

        // 2. Translate legacy '&' codes
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Translates all color codes in a list of strings.
     * <p>
     * This method creates a new list containing the translated strings.
     * The original list remains unchanged.
     * </p>
     *
     * @param messages The list of strings to translate. May be null.
     * @return A new list with translated strings, or null if the input was null.
     */
    public static List<String> translate(List<String> messages) {
        if (messages == null) {
            return null;
        }
        return messages.stream()
                .map(ColorAPI::translate)
                .collect(Collectors.toList());
    }

    /**
     * Translates all color codes in a list of strings, modifying the list in place.
     * <p>
     * This is more memory-efficient than {@link #translate(List)} when you don't need
     * to keep the original list.
     * </p>
     *
     * @param messages The list of strings to translate. If null, nothing happens.
     */
    public static void translateInPlace(List<String> messages) {
        if (messages == null) {
            return;
        }
        messages.replaceAll(ColorAPI::translate);
    }

    /**
     * Checks whether the current server supports HEX colors (Minecraft 1.16+).
     *
     * @return true if HEX colors are supported, false otherwise.
     */
    public static boolean isHexSupported() {
        return IS_HEX_SUPPORTED;
    }

    /**
     * Internal method to replace HEX color codes with their ChatColor equivalent.
     * Uses a pre-compiled Pattern and a StringBuffer for efficient replacement.
     *
     * @param input The string containing potential HEX codes.
     * @return The string with HEX codes replaced by formatting codes.
     */
    private static String replaceHexColors(String input) {
        Matcher matcher = HEX_PATTERN.matcher(input);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            String mcColor = toMinecraftHex(hex);
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(mcColor));
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static String toMinecraftHex(String hex) {
        StringBuilder out = new StringBuilder("§x");
        for (char c : hex.toCharArray()) {
            out.append('§').append(c);
        }
        return out.toString();
    }

    /**
     * Performs a version check to determine HEX color support.
     * Parses {@link Bukkit#getBukkitVersion()} to extract the Minecraft version.
     * Falls back to {@code true} (assume modern server) if parsing fails.
     *
     * @return true if server version is 1.16 or higher.
     */
    private static boolean checkHexSupport() {
        try {
            // Example: "1.16.5-R0.1-SNAPSHOT" -> "1.16.5"
            String version = Bukkit.getBukkitVersion().split("-")[0];
            String[] parts = version.split("\\.");
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);
            return (major > 1) || (major == 1 && minor >= 16);
        } catch (Exception e) {
            // If parsing fails, assume a modern server to be safe
            return true;
        }
    }
}