package net.aunacraft.bungeemotd.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataContainer {

    private String normalMotd;
    private String maintenanceMotd;
    private String playerInfo;
    private String maintenancePlayerInfo;
    private String maintenanceUnconnectableMessage;
    private boolean maintenance;

    public DataContainer(String normalMotd, String maintenanceMotd, String playerInfo, String maintenancePlayerInfo, String maintenanceUnconnectableMessage) {
        this.normalMotd = normalMotd;
        this.maintenanceMotd = maintenanceMotd;
        this.playerInfo = playerInfo;
        this.maintenancePlayerInfo = maintenancePlayerInfo;
        this.maintenanceUnconnectableMessage = maintenanceUnconnectableMessage;
        this.maintenance = false;
    }

    public static DataContainer fromString(String serializedData) {
        return new Gson().fromJson(serializedData, DataContainer.class);
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public String getNormalMotd() {
        return normalMotd;
    }

    public void setNormalMotd(String normalMotd) {
        this.normalMotd = normalMotd;
    }

    public String getMaintenanceMotd() {
        return maintenanceMotd;
    }

    public void setMaintenanceMotd(String maintenanceMotd) {
        this.maintenanceMotd = maintenanceMotd;
    }

    public String getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(String playerInfo) {
        this.playerInfo = playerInfo;
    }

    public String getMaintenancePlayerInfo() {
        return maintenancePlayerInfo;
    }

    public void setMaintenancePlayerInfo(String maintenancePlayerInfo) {
        this.maintenancePlayerInfo = maintenancePlayerInfo;
    }

    public String getMaintenanceUnconnectableMessage() {
        return maintenanceUnconnectableMessage;
    }

    public void setMaintenanceUnconnectableMessage(String maintenanceUnconnectableMessage) {
        this.maintenanceUnconnectableMessage = maintenanceUnconnectableMessage;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
