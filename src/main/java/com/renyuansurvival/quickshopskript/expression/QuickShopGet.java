package com.renyuansurvival.quickshopskript.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.renyuansurvival.quickshopskript.QuickshopSkript;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.api.shop.Shop;

@Name("get QuickShop")
@Description("Returns the shop of the block")
public class QuickShopGet extends SimpleExpression<Shop> {

    static {
        Skript.registerExpression(QuickShopGet.class, Shop.class, ExpressionType.COMBINED,
                "[the] (shop) of [the] %block%");
    }

    private Expression<Block> block;

    @Override
    protected Shop [] get(@NotNull Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        return shop != null ? new Shop[]{shop} : null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Shop> getReturnType() {
        return Shop.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        if(e == null){
            return "null";
        }
        return String.valueOf(QuickshopSkript.getShop(block.getSingle(e)));
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}