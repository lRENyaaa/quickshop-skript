package com.renyuansurvival.quickshopskript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.maxgamer.quickshop.api.event.ShopClickEvent;

public class ClickShopEvent {
    static {
        Skript.registerEvent("quickshop click event", SimpleEvent.class, ShopClickEvent.class, "quickshop click");
        EventValues.registerEventValue(ShopClickEvent.class, Block.class, new Getter<Block, ShopClickEvent>() {
            @Override
            public Block get(ShopClickEvent event) {
                return event.getShop().getLocation().getBlock();
            }
        }, 0);
    }
}
