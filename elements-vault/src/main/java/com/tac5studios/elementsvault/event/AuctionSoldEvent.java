package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.Money;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;

import java.util.UUID;

/** An auction house listing sold (buy-now or winning bid). */
public class AuctionSoldEvent extends Event {

    private final UUID seller;
    private final UUID buyer;
    private final String listingId;
    private final ItemStack item;
    private final Money price;

    public AuctionSoldEvent(UUID seller, UUID buyer, String listingId, ItemStack item, Money price) {
        this.seller = seller;
        this.buyer = buyer;
        this.listingId = listingId;
        this.item = item.copy();
        this.price = price;
    }

    public UUID seller() { return seller; }

    public UUID buyer() { return buyer; }

    public String listingId() { return listingId; }

    public ItemStack item() { return item.copy(); }

    public Money price() { return price; }
}
