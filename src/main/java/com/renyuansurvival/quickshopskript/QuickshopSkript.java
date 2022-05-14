package com.renyuansurvival.quickshopskript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxgamer.quickshop.api.QuickShopAPI;
import org.maxgamer.quickshop.api.shop.Shop;

import java.io.IOException;

public final class QuickshopSkript extends JavaPlugin {

    public static QuickShopAPI api;

    @Override
    public void onEnable() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("QuickShop");
        if(plugin != null){
            api = (QuickShopAPI)plugin;
        }
        try {
            SkriptAddon addonInstance = Skript.registerAddon(this);
            addonInstance.loadClasses("com.renyuansurvival.quickshopskript","conditions","expression","effect","event");
        } catch (IOException e) {
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public static Shop getShop(Block block){
        if(block != null) {
            Shop shop = api.getShopManager().getShop(block.getLocation());
            if(shop != null){
                return shop;
            }
            BlockData Drops = block.getBlockData();
            if(Drops instanceof Directional) {
                return api.getShopManager().getShop(block.getRelative(((Directional) Drops).getFacing(), -1).getLocation());
            }
        }
        return null;
    }
}
