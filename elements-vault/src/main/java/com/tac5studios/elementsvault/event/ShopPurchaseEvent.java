package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.Money;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A player bought from or sold to a shop.
 *
 * @see #playerBought() true when the player bought, false when they sold to the shop
 */
public class ShopPurchaseEvent extends Event {

    private final UUID player;
    @Nullable
    private final UUID owner;
    private final String shopId;
    private final ItemStack item;
    private final int count;
    private final Money price;
    private final boolean playerBought;

    public ShopPurchaseEvent(UUID player, @Nullable UUID owner, String shopId, ItemStack item, int count, Money price, boolean playerBought) {
        this.player = player;
        this.owner = owner;
        this.shopId = shopId;
        this.item = item.copy();
        this.count = count;
        this.price = price;
        this.playerBought = playerBought;
    }

    public UUID player() { return player; }

    /** The shop owner, or null for server shops. */
    @Nullable
    public UUID owner() { return owner; }

    public String shopId() { return shopId; }

    public ItemStack item() { return item.copy(); }

    public int count() { return count; }

    /** Total paid. */
    public Money price() { return price; }

    public boolean playerBought() { return playerBought; }
}
