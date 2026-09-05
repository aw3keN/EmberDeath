package com.aw3ken.emberdeath.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public final class MessageFormatter {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public Component format(String value, String playerName) {
        return miniMessage.deserialize(normalize(value).replace("\\n", "\n"),
                Placeholder.unparsed("name", playerName));
    }

    public String plainText(String value, String playerName) {
        return PlainTextComponentSerializer.plainText().serialize(format(value, playerName));
    }

    private String normalize(String value) {
        String normalized = value.replaceAll("&#([A-Fa-f0-9]{6})", "<#$1>");
        String[][] legacyTags = {
                {"0", "black"}, {"1", "dark_blue"}, {"2", "dark_green"}, {"3", "dark_aqua"},
                {"4", "dark_red"}, {"5", "dark_purple"}, {"6", "gold"}, {"7", "gray"},
                {"8", "dark_gray"}, {"9", "blue"}, {"a", "green"}, {"b", "aqua"},
                {"c", "red"}, {"d", "light_purple"}, {"e", "yellow"}, {"f", "white"},
                {"k", "obfuscated"}, {"l", "bold"}, {"m", "strikethrough"},
                {"n", "underlined"}, {"o", "italic"}, {"r", "reset"}
        };
        for (String[] legacyTag : legacyTags) {
            normalized = normalized.replace("&" + legacyTag[0], "<" + legacyTag[1] + ">");
        }
        return normalized;
    }
}
