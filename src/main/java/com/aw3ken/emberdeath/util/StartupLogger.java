package com.aw3ken.emberdeath.util;

import com.aw3ken.emberdeath.config.EmberDeathSettings;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StartupLogger {
    private StartupLogger() {
    }

    public static void log(Logger logger, JavaPlugin plugin, EmberDeathSettings settings) {
        String line = "============================================================";
        logger.info(line);
        logger.info("  EMBERDEATH | Death punishment and local alert system");
        logger.info(line);
        logger.info("  Version: " + plugin.getDescription().getVersion());
        logger.info("  Author: aw3keN");
        logger.info("  Server: " + Bukkit.getName() + " " + Bukkit.getBukkitVersion());
        logger.info("  Death message: " + status(settings.messageEnabled()) + " | radius " + settings.messageRadius() + " blocks");
        logger.info("  Death sound: " + status(settings.soundEnabled()) + " | " + settings.sound().name()
                + " | radius " + settings.soundRadius() + " blocks");
        logger.info("  Punishment: " + status(settings.punishmentEnabled()) + " | mode " + settings.punishmentMode().name());
        logger.info("  Configuration loaded successfully.");
        logger.info("  EmberDeath is ready to protect your server.");
        logger.info(line);
    }

    private static String status(boolean enabled) {
        return enabled ? "ENABLED" : "DISABLED";
    }
}
