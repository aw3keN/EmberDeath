package com.aw3ken.emberdeath.version;

import org.bukkit.Bukkit;

public final class ServerVersionAdapters {
    private ServerVersionAdapters() { }

    public static ServerVersionAdapter detect() {
        String version = Bukkit.getBukkitVersion();
        if (version.startsWith("1.16")) return new com.aw3ken.emberdeath.version.v1_16_5.ServerVersionAdapterImpl();
        if (version.startsWith("1.17")) return new com.aw3ken.emberdeath.version.v1_17.ServerVersionAdapterImpl();
        if (version.startsWith("1.18")) return new com.aw3ken.emberdeath.version.v1_18.ServerVersionAdapterImpl();
        if (version.startsWith("1.19")) return new com.aw3ken.emberdeath.version.v1_19.ServerVersionAdapterImpl();
        if (version.startsWith("1.20")) return new com.aw3ken.emberdeath.version.v1_20.ServerVersionAdapterImpl();
        if (version.startsWith("1.21")) return new com.aw3ken.emberdeath.version.v1_21.ServerVersionAdapterImpl();
        if (version.startsWith("26.1")) return new com.aw3ken.emberdeath.version.v26_1.ServerVersionAdapterImpl();
        if (version.startsWith("26.2")) return new com.aw3ken.emberdeath.version.v26_2.ServerVersionAdapterImpl();
        return new com.aw3ken.emberdeath.version.v1_16_5.ServerVersionAdapterImpl();
    }
}
