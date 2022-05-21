package com.renyuansurvival.quickshopskript.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.renyuansurvival.quickshopskript.QuickshopSkript;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.maxgamer.quickshop.api.shop.Shop;
import org.maxgamer.quickshop.api.shop.ShopType;

public class SellShopSet extends Effect {

    static {
        Skript.registerEffect(SellShopSet.class, "set shop %block% [to] selling");
    }

    private Expression<Block> block;

    @Override
    protected void execute( Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        if (shop != null) shop.setShopType(ShopType.SELLING);
    }

    @Override
    public String toString( Event e, boolean b) {
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}