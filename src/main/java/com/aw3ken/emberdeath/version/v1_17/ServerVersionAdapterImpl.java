package com.aw3ken.emberdeath.version.v1_17;

import com.aw3ken.emberdeath.version.ServerVersionAdapter;
import org.bukkit.entity.Player;

public final class ServerVersionAdapterImpl implements ServerVersionAdapter {
    public String versionId() { return "1.17"; }
    public void disconnect(Player player, String reason) { player.kickPlayer(reason); }
}
