package com.aw3ken.emberdeath.version;

import org.bukkit.entity.Player;

public interface ServerVersionAdapter {
    String versionId();
    void disconnect(Player player, String reason);
}
