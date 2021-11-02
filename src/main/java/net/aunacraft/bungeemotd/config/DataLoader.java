package net.aunacraft.bungeemotd.config;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataLoader {

    private final DataContainer container;

    public DataContainer getContainer() {
        return container;
    }

    public DataLoader(Plugin plugin) throws IOException {
        // Creating Plugin DataFolder if doesn´t exist
        if(!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        final File dataFile = new File("plugins\\" + plugin.getDataFolder().getName() + "\\config.json");

        if(!dataFile.exists()){
            dataFile.createNewFile();

            final FileWriter writer = new FileWriter(dataFile);
            final DataContainer defaultContainer = this.getDefaultContainer();

            writer.write(defaultContainer.toString());
            writer.flush();
            writer.close();

            this.container = defaultContainer;
        }else{
            Scanner scanner = new Scanner(dataFile);
            StringBuilder fileContent = new StringBuilder();

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                fileContent.append(line);
            }

            this.container = DataContainer.fromString(fileContent.toString());
        }


    }

    private DataContainer getDefaultContainer(){
        return new DataContainer(
                "§cThis is the Motd!",
                "§cThis is the maintenance motd",
                "Here are some players",
                "Here are no players",
                "Maintenance"
        );
    }
}
