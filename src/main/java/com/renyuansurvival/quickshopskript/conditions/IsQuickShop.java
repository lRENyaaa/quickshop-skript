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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


@Name("Is Quickshop")
@Description("Condition to check if a block is Quickshop")
public class IsQuickShop extends Condition {

    static {
        Skript.registerCondition(IsQuickShop.class, "%block% (1¦is|2¦is(n't| not)) quickshop");
    }

    private Expression<Block> block;
    private boolean type;

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "Is Quickshop: " + block.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        type = parseResult.mark == 1;
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        for (Block block : this.block.getAll(e)){
             if (QuickshopSkript.getShop(block) != null) return type;
        }
        return !type;
    }
}