package com.tac5studios.elementsvault;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Who or what moved the money. Shown in logs and history, and passed to events.
 *
 * @param type   what started it
 * @param actor  the player who started it, if any
 * @param source the shop, listing, command or mod id
 * @param reason free text, e.g. "for the iron"
 */
public record Cause(Type type, @Nullable UUID actor, String source, String reason) {

    public enum Type { PLAYER, SHOP, AUCTION, COMMAND, PLUGIN, SYSTEM }

    public static Cause player(UUID player, String reason) {
        return new Cause(Type.PLAYER, player, "", reason);
    }

    public static Cause shop(String bridgeId, BlockPos pos, @Nullable UUID player) {
        return new Cause(Type.SHOP, player, bridgeId + "@" + pos.toShortString(), "");
    }

    public static Cause shop(String shopId, @Nullable UUID player) {
        return new Cause(Type.SHOP, player, shopId, "");
    }

    public static Cause auction(String listingId, @Nullable UUID player) {
        return new Cause(Type.AUCTION, player, listingId, "");
    }

    public static Cause command(String command, @Nullable UUID player) {
        return new Cause(Type.COMMAND, player, command, "");
    }

    public static Cause plugin(String modId) {
        return new Cause(Type.PLUGIN, null, modId, "");
    }

    public static Cause system(String reason) {
        return new Cause(Type.SYSTEM, null, "", reason);
    }

    public Cause withReason(String text) {
        return new Cause(type, actor, source, text);
    }
}
