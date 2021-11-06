package net.aunacraft.bungeemotd.listeners;

import net.aunacraft.bungeemotd.BungeeMOTD;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    @EventHandler
    public void handleJoin(PostLoginEvent event){
        if(event.getPlayer().getName().equalsIgnoreCase("ytendx")){
            event.getPlayer().sendMessage(new TextComponent("The Server is using BungeeMOTD of yourself xD"));
        }
    }

}
