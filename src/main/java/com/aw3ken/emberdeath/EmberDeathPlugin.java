package com.aw3ken.emberdeath;

import com.aw3ken.emberdeath.config.EmberDeathSettings;
import com.aw3ken.emberdeath.listener.PlayerDeathListener;
import com.aw3ken.emberdeath.service.DeathEffectService;
import com.aw3ken.emberdeath.util.StartupLogger;
import org.bukkit.plugin.java.JavaPlugin;

public final class EmberDeathPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        EmberDeathSettings settings = EmberDeathSettings.load(getConfig(), getLogger());
        DeathEffectService effects = new DeathEffectService(this, settings);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(settings, effects), this);
        StartupLogger.log(getLogger(), this, settings);
    }

    @Override
    public void onDisable() {
        getLogger().info("EmberDeath has been disabled.");
    }
}
