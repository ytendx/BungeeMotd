package net.aunacraft.bungeemotd;

import net.aunacraft.bungeemotd.config.DataLoader;
import net.aunacraft.bungeemotd.listeners.LoginListener;
import net.aunacraft.bungeemotd.listeners.PingListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class BungeeMOTD extends Plugin {

    private static BungeeMOTD pluginInstance;
    private final String CONSOLE_PREFIX = "[BungeeMOTD] ";
    private DataLoader dataLoader;

    public static BungeeMOTD getPluginInstance() {
        return pluginInstance;
    }

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    public boolean getMaintenance() {
        return dataLoader.getContainer().isMaintenance();
    }

    public void setMaintenance(boolean maintenance) {
        dataLoader.getContainer().setMaintenance(maintenance);
    }

    @Override
    public void onEnable() {

        this.getLogger().info(CONSOLE_PREFIX + "Enabling BungeeMOTD by ytendx...");

        pluginInstance = this;

        try {
            this.dataLoader = new DataLoader(this);
            this.getLogger().info(CONSOLE_PREFIX + "Succesfully loaded Data from Config!");
        } catch (IOException e) {
            this.getLogger().warning(CONSOLE_PREFIX + "Couldn´t initialize dataloader.");
            e.printStackTrace();
            this.getLogger().warning(CONSOLE_PREFIX + "Shutting down BungeeMOTD...");
            this.onDisable();
        }

        new PingListener(this);
        this.getProxy().getPluginManager().registerListener(this, new LoginListener());
        super.onEnable();

        this.getLogger().info(CONSOLE_PREFIX + "Enabled BungeeMOTD by ytendx succesfully.");
        this.getLogger().info(CONSOLE_PREFIX + "GitHub: https://github.com/ytendx");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
