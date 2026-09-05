package com.aw3ken.emberdeath.service;

import com.aw3ken.emberdeath.config.EmberDeathSettings;
import com.aw3ken.emberdeath.util.MessageFormatter;
import com.aw3ken.emberdeath.version.ServerVersionAdapter;
import org.bukkit.BanList;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathEffectService {
    private final JavaPlugin plugin;
    private final EmberDeathSettings settings;
    private final MessageFormatter formatter = new MessageFormatter();
    private final ServerVersionAdapter versionAdapter;

    public DeathEffectService(JavaPlugin plugin, EmberDeathSettings settings) {
        this.plugin = plugin;
        this.settings = settings;
        this.versionAdapter = com.aw3ken.emberdeath.version.ServerVersionAdapters.detect();
    }

    public void broadcastMessage(Player deceased) {
        if (!settings.messageEnabled()) {
            return;
        }
        double maxDistanceSquared = settings.messageRadius() * settings.messageRadius();
        for (Player recipient : deceased.getWorld().getPlayers()) {
            if ((!settings.includeDeceased() && recipient.equals(deceased))
                    || recipient.getLocation().distanceSquared(deceased.getLocation()) > maxDistanceSquared) {
                continue;
            }
            recipient.sendMessage(formatter.format(settings.messageFormat(), deceased.getName()));
        }
    }

    public void playSound(Player deceased) {
        if (!settings.soundEnabled()) {
            return;
        }
        double maxDistanceSquared = settings.soundRadius() * settings.soundRadius();
        for (Player recipient : deceased.getWorld().getPlayers()) {
            if (recipient.getLocation().distanceSquared(deceased.getLocation()) <= maxDistanceSquared) {
                recipient.playSound(deceased.getLocation(), settings.sound(), settings.soundVolume(), settings.soundPitch());
            }
        }
    }

    public void applyPunishment(Player deceased) {
        if (!settings.punishmentEnabled()) {
            return;
        }
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (!deceased.isOnline()) {
                return;
            }
            String name = deceased.getName();
            if (settings.punishmentMode() == EmberDeathSettings.PunishmentMode.BAN) {
                // Add the ban without letting Bukkit disconnect the player first.
                // The following component kick preserves colors on the client ban screen.
                plugin.getServer().getBanList(BanList.Type.NAME)
                        .addBan(name, formatter.format(settings.banReason(), name), null, plugin.getName());
                versionAdapter.disconnect(deceased, formatter.format(settings.banReason(), name));
            } else {
                versionAdapter.disconnect(deceased, formatter.format(settings.kickReason(), name));
            }
        });
    }
}
