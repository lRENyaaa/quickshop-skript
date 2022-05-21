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
import org.bukkit.event.Event;


@Name("Is Quickshop")
@Description("Condition to check if a block is Quickshop")
public class IsQuickShop extends Condition {

    static {
        Skript.registerCondition(IsQuickShop.class, "%block% (1¦is|2¦is(n't| not)) quickshop");
    }

    private Expression<Block> block;
    private boolean type;

    @Override
    public String toString(Event e, boolean debug) {
        return "Is Quickshop: " + block.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> [] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        type = parseResult.mark == 1;
        return true;
    }

    @Override
    public boolean check(Event e) {
        for (Block block : this.block.getAll(e)){
             if (QuickshopSkript.getShop(block) != null) return type;
        }
        return !type;
    }
}
