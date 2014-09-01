package ru.gtncraft.weathercontrol;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldLoadEvent;

import java.util.WeakHashMap;

class Listeners implements Listener {
    private final WeatherControl plugin;
    private final WeakHashMap<World, Boolean> worlds = new WeakHashMap<>();

    public Listeners(final WeatherControl plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getWorlds().forEach(this::init);
    }

    void init(final World world) {
        Boolean prevent = plugin.getWorlds().get(world.getName());
        if (prevent != null) {
            worlds.put(world, prevent);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onWorldLoad(final WorldLoadEvent event) {
        init(event.getWorld());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onWeatherChange(final WeatherChangeEvent event) {
        Boolean weather = worlds.get(event.getWorld());
        if (weather != null) {
            event.setCancelled(weather);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onLightningStrike(final LightningStrikeEvent event) {
        Boolean weather = worlds.get(event.getWorld());
        if (weather != null) {
            event.setCancelled(weather);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("unused")
    void onThunderChange(final ThunderChangeEvent event) {
        Boolean weather = worlds.get(event.getWorld());
        if (weather != null) {
            event.setCancelled(weather);
        }
    }
}
