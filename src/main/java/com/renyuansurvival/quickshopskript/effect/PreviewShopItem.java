package com.renyuansurvival.quickshopskript.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.renyuansurvival.quickshopskript.QuickshopSkript;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.maxgamer.quickshop.api.shop.Shop;

public class PreviewShopItem extends Effect {

    static {
        Skript.registerEffect(PreviewShopItem.class, "open [the] shop %block% preview to [the] %player%");
    }

    private Expression<Block> block;
    private Expression<Player> player;

    @Override
    protected void execute(Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        if (shop != null && player.getSingle(e) != null ) shop.openPreview(player.getSingle(e));
    }

    @Override
    public String toString(Event e, boolean b) {
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        player = (Expression<Player>) exprs[1];
        return true;
    }
}

