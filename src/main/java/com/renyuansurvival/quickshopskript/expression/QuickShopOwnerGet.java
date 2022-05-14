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
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.api.shop.Shop;


@Name("get QuickShop owner")
@Description("Returns the owner of the shop block")
public class QuickShopOwnerGet extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(QuickShopOwnerGet.class, Player.class, ExpressionType.COMBINED,
                "[the] owner of [the] shop %block%");
    }

    private Expression<Block> block;

    @Override
    protected Player[] get(@NotNull Event e) {
        Shop shop = QuickshopSkript.getShop(block.getSingle(e));
        return shop != null && Bukkit.getPlayer(shop.getOwner()) != null ? new Player[]{Bukkit.getPlayer(shop.getOwner())} : null;
    }
    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        if(e == null || QuickshopSkript.getShop(block.getSingle(e)) == null){
            return "null";
        }
        return Bukkit.getPlayer(QuickshopSkript.getShop(block.getSingle(e)).getOwner()).getName();
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        block = (Expression<Block>) exprs[0];
        return true;
    }
}