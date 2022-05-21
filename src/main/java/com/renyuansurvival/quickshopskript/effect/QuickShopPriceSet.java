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

@Name("Set QuickShop price")
@Description("Set QuickShop price from block")
public class QuickShopPriceSet extends Effect {

    static {
        Skript.registerEffect(QuickShopPriceSet.class, "shop %block%['s] price set to %string%");
    }

    private Expression<Block> block;
    private Expression<String> price;

    @Override
    protected void execute(Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        Double shopPrice = null;
        if(price.getSingle(e) != null) {
            try {
                shopPrice = Double.valueOf(price.getSingle(e));
            } catch (NumberFormatException error){
                shopPrice = null;
            }
        }
        if (shop != null && shopPrice != null ) shop.setPrice(shopPrice);
    }

    @Override
    public String toString(Event e, boolean b) {
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        price = (Expression<String>) exprs[1];
        return true;
    }
}
