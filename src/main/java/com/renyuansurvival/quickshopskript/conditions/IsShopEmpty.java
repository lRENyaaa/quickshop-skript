package com.renyuansurvival.quickshopskript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.renyuansurvival.quickshopskript.QuickshopSkript;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.maxgamer.quickshop.api.shop.Shop;

@Name("Is Shop Empty")
@Description("Condition to check if Quickshop is Empty")
public class IsShopEmpty extends Condition {

    static {
        Skript.registerCondition(IsShopEmpty.class, "shop %block% (1¦is|2¦is(n't| not)) empty");
    }

    private Expression<Block> block;
    private boolean type;

    @Override
    public String toString( Event e, boolean debug) {
        return "Quickshop: " + block.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> [] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        type = parseResult.mark == 1;
        return true;
    }

    @Override
    public boolean check( Event e) {
        for (Block block : this.block.getAll(e)){
            Shop shop = QuickshopSkript.getShop(block);
            if ( shop == null ) return type;
            Inventory shopInventory = ((Chest) shop.getLocation().getBlock().getState()).getBlockInventory();
            if (shopInventory.isEmpty()) return type;
            for (ItemStack item : shopInventory.getContents()){
                if ( item != null && item.getType() == shop.getItem().getType() && String.valueOf(item.getItemMeta()).equals(String.valueOf(shop.getItem().getItemMeta()))) return !type;
            }
        }
        return type;
    }
}
