package com.aw3ken.emberdeath.config;

import java.util.Locale;
import java.util.logging.Logger;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public record EmberDeathSettings(
        boolean messageEnabled,
        double messageRadius,
        boolean includeDeceased,
        String messageFormat,
        boolean soundEnabled,
        Sound sound,
        double soundRadius,
        float soundVolume,
        float soundPitch,
        boolean punishmentEnabled,
        PunishmentMode punishmentMode,
        String kickReason,
        String banReason
) {
    public static EmberDeathSettings load(FileConfiguration config, Logger logger) {
        Sound sound;
        String soundName = config.getString("death-sound.sound", "ENTITY_ELDER_GUARDIAN_CURSE");
        try {
            sound = Sound.valueOf(soundName.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            logger.warning("Invalid death-sound.sound value '" + soundName
                    + "'. Using ENTITY_ELDER_GUARDIAN_CURSE.");
            sound = Sound.ENTITY_ELDER_GUARDIAN_CURSE;
        }

        PunishmentMode mode = PunishmentMode.from(config.getString("punishment.mode", "KICK"), logger);
        return new EmberDeathSettings(
                config.getBoolean("death-message.enabled", true),
                nonNegative(config.getDouble("death-message.radius", 100.0)),
                config.getBoolean("death-message.include-deceased", false),
                config.getString("death-message.format", "<red>Player <name> died.</red>"),
                config.getBoolean("death-sound.enabled", true),
                sound,
                nonNegative(config.getDouble("death-sound.radius", 100.0)),
                nonNegativeFloat(config.getDouble("death-sound.volume", 1.0)),
                nonNegativeFloat(config.getDouble("death-sound.pitch", 1.0)),
                config.getBoolean("punishment.enabled", true),
                mode,
                config.getString("punishment.kick-reason", "<red>You died.</red>"),
                config.getString("punishment.ban-reason", "<red>You have been banned after dying.</red>")
        );
    }

    private static double nonNegative(double value) {
        return Math.max(0.0, value);
    }

    private static float nonNegativeFloat(double value) {
        return (float) Math.max(0.0, value);
    }

    public enum PunishmentMode {
        KICK, BAN;

        static PunishmentMode from(String value, Logger logger) {
            try {
                return valueOf(value.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                logger.warning("Invalid punishment.mode value '" + value + "'. Using KICK.");
                return KICK;
            }
        }
    }
}
