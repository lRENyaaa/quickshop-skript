package com.renyuansurvival.quickshopskript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;



public class AnimationEvent {
    static {

        Skript.registerEvent("animation event", SimpleEvent.class, PlayerAnimationEvent.class, "animation");
        EventValues.registerEventValue(PlayerAnimationEvent.class, Block.class, new Getter<Block, PlayerAnimationEvent>() {
            @Override
            public Block get(PlayerAnimationEvent event) {
                return event.getPlayer().getTargetBlock(null, 5);
            }
        }, 0);
        EventValues.registerEventValue(PlayerAnimationEvent.class, Player.class, new Getter<Player, PlayerAnimationEvent>() {
            @Override
            public Player get(PlayerAnimationEvent event) {
                return event.getPlayer();
            }
        }, 0);
    }
}
