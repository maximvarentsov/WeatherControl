package ru.gtncraft.weathercontrol;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class WeatherControl extends JavaPlugin {
    private final Map<String, Boolean> worlds = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        for (Map.Entry<String, Object> entry : getConfig().getValues(false).entrySet()) {
            String world = entry.getKey();
            boolean radius = (boolean) entry.getValue();
            worlds.put(world, radius);
        }
        new Listeners(this);
    }

    public Map<String, Boolean> getWorlds() {
        return worlds;
    }
}
