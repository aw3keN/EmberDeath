package com.aw3ken.emberdeath.version.v26_2;

import com.aw3ken.emberdeath.version.ServerVersionAdapter;
import org.bukkit.entity.Player;

public final class ServerVersionAdapterImpl implements ServerVersionAdapter {
    public String versionId() { return "26.2"; }
    public void disconnect(Player player, String reason) { player.kickPlayer(reason); }
}
