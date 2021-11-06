package net.aunacraft.bungeemotd.config;

import net.aunacraft.bungeemotd.utils.FileUtil;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.util.Scanner;

public class DataLoader {

    private final DataContainer container;

    public DataLoader(Plugin plugin) throws IOException {
        // Creating Plugin DataFolder if doesn´t exist
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        final File dataFile = new File(plugin.getDataFolder(), "config.json");

        if (!dataFile.exists()) {
            dataFile.createNewFile();

            final FileWriter writer = new FileWriter(dataFile);
            final DataContainer defaultContainer = this.getDefaultContainer();

            writer.write(defaultContainer.toString());
            writer.flush();
            writer.close();

            this.container = defaultContainer;
        } else {
            this.container = DataContainer.fromString(FileUtil.getFileContent(dataFile, this.getDefaultContainer().toString()));
        }


    }

    public DataContainer getContainer() {
        return container;
    }

    private DataContainer getDefaultContainer() {
        return new DataContainer(
                "§cThis is the Motd!",
                "§cThis is the maintenance motd",
                "Here are some players",
                "Here are no players",
                "Maintenance"
        );
    }
}
