package net.aunacraft.bungeemotd;

import net.aunacraft.bungeemotd.config.DataLoader;
import net.aunacraft.bungeemotd.listeners.PingListener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class BungeeMOTD extends Plugin {

    private DataLoader dataLoader;

    public DataLoader getDataLoader() {
        return dataLoader;
    }

    private static BungeeMOTD pluginInstance;
    public static BungeeMOTD getPluginInstance() {
        return pluginInstance;
    }

    public boolean getMaintenance(){
        return dataLoader.getContainer().isMaintenance();
    }
    
    public void setMaintenance(boolean maintenance){
        dataLoader.getContainer().setMaintenance(maintenance);
    }

    @Override
    public void onEnable() {
        pluginInstance = this;

        try {
            this.dataLoader = new DataLoader(this);
        } catch (IOException e) {
            this.getLogger().warning("Couldn´t initialize dataloader.");
            e.printStackTrace();
            this.getLogger().warning("Shutting down BungeeMOTD...");
            this.onDisable();
        }

        new PingListener(this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
