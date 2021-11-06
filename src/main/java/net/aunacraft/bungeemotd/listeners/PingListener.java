package net.aunacraft.bungeemotd.listeners;

import net.aunacraft.bungeemotd.BungeeMOTD;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class PingListener implements Listener {

    public PingListener(Plugin plugin) {
        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }

    @EventHandler
    public void handlePing(ProxyPingEvent event) {
        if (BungeeMOTD.getPluginInstance().getDataLoader().getContainer().isMaintenance()) {
            ServerPing ping = event.getResponse();
            ping.getPlayers().setSample(new ServerPing.PlayerInfo[]{
                    new ServerPing.PlayerInfo(BungeeMOTD.getPluginInstance().getDataLoader().getContainer().getMaintenancePlayerInfo(), UUID.randomUUID())
            });
            ping.setVersion(new ServerPing.Protocol(BungeeMOTD.getPluginInstance().getDataLoader().getContainer().getMaintenanceUnconnectableMessage(), 0));
            ping.setDescriptionComponent(new TextComponent(BungeeMOTD.getPluginInstance().getDataLoader().getContainer().getMaintenanceMotd()));
            event.setResponse(ping);
        } else {
            ServerPing ping = event.getResponse();
            ping.getPlayers().setSample(new ServerPing.PlayerInfo[]{
                    new ServerPing.PlayerInfo(BungeeMOTD.getPluginInstance().getDataLoader().getContainer().getPlayerInfo(), UUID.randomUUID())
            });
            ping.setDescriptionComponent(new TextComponent(BungeeMOTD.getPluginInstance().getDataLoader().getContainer().getNormalMotd()));
            event.setResponse(ping);
        }
    }

}
