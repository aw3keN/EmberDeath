package com.aw3ken.emberdeath.listener;

import com.aw3ken.emberdeath.config.EmberDeathSettings;
import com.aw3ken.emberdeath.service.DeathEffectService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class PlayerDeathListener implements Listener {
    private final EmberDeathSettings settings;
    private final DeathEffectService effects;

    public PlayerDeathListener(EmberDeathSettings settings, DeathEffectService effects) {
        this.settings = settings;
        this.effects = effects;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deceased = event.getEntity();
        effects.broadcastMessage(deceased);
        effects.playSound(deceased);
        effects.applyPunishment(deceased);
    }
}
