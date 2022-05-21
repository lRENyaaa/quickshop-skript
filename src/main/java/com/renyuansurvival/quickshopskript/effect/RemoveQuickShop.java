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
import org.maxgamer.quickshop.api.shop.Shop;

@Name("Remove QuickShop")
@Description("Remove QuickShop from block")
public class RemoveQuickShop extends Effect {

    static {
        Skript.registerEffect(RemoveQuickShop.class, "remove [this] shop %block%");
    }

    private Expression<Block> block;

    @Override
    protected void execute(Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        if (shop != null) shop.delete();
    }

    @Override
    public String toString(Event e, boolean b) {
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}