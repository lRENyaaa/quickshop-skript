package com.renyuansurvival.quickshopskript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.lucko.helper.cooldown.Cooldown;
import me.lucko.helper.cooldown.CooldownMap;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

public class AnimationEvent {
    static {
        final CooldownMap<Player> cooldownMap = CooldownMap.create(Cooldown.of(1, TimeUnit.SECONDS));
        Skript.registerEvent("animation event", SimpleEvent.class, PlayerAnimationEvent.class, "animation");
        EventValues.registerEventValue(PlayerAnimationEvent.class, Block.class, new Getter<Block, PlayerAnimationEvent>() {
            @Override
            public @Nullable Block get(PlayerAnimationEvent event) {
                if (!cooldownMap.test(event.getPlayer())) {
                    return null;
                }
                return event.getPlayer().getTargetBlock(null, 5);
            }
        }, 0);
        EventValues.registerEventValue(PlayerAnimationEvent.class, Player.class, new Getter<Player, PlayerAnimationEvent>() {
            @Override
            public @Nullable Player get(PlayerAnimationEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }
}
