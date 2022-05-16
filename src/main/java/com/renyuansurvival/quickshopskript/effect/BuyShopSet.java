package com.renyuansurvival.quickshopskript.effect;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.renyuansurvival.quickshopskript.QuickshopSkript;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.api.shop.Shop;
import org.maxgamer.quickshop.api.shop.ShopType;

public class BuyShopSet extends Effect {

    static {
        Skript.registerEffect(BuyShopSet.class, "set shop %block% [to] buying");
    }

    private Expression<Block> block;

    @Override
    protected void execute(@NotNull Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        if (shop != null) shop.setShopType(ShopType.BUYING);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean b) {
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}