package com.aw3ken.emberdeath.util;

import org.bukkit.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MessageFormatter {
    private static final Pattern HEX_COLOR = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public String format(String value, String playerName) {
        return ChatColor.translateAlternateColorCodes('&', normalize(value)
                .replace("<name>", playerName)
                .replace("\\n", "\n"));
    }

    private String normalize(String value) {
        String normalized = value;
        Matcher matcher = HEX_COLOR.matcher(normalized);
        StringBuffer colors = new StringBuffer();
        while (matcher.find()) {
            String hex = matcher.group(1);
            StringBuilder replacement = new StringBuilder("§x");
            for (char digit : hex.toCharArray()) {
                replacement.append('§').append(digit);
            }
            matcher.appendReplacement(colors, Matcher.quoteReplacement(replacement.toString()));
        }
        matcher.appendTail(colors);
        normalized = colors.toString();
        String[][] legacyTags = {
                {"0", "black"}, {"1", "dark_blue"}, {"2", "dark_green"}, {"3", "dark_aqua"},
                {"4", "dark_red"}, {"5", "dark_purple"}, {"6", "gold"}, {"7", "gray"},
                {"8", "dark_gray"}, {"9", "blue"}, {"a", "green"}, {"b", "aqua"},
                {"c", "red"}, {"d", "light_purple"}, {"e", "yellow"}, {"f", "white"},
                {"k", "obfuscated"}, {"l", "bold"}, {"m", "strikethrough"},
                {"n", "underlined"}, {"o", "italic"}, {"r", "reset"}
        };
        for (String[] legacyTag : legacyTags) {
            normalized = normalized.replace("<" + legacyTag[1] + ">", "&" + legacyTag[0]);
        }
        return normalized;
    }
}
