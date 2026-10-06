package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.Money;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;

import java.util.UUID;

/** A player listed an item in the auction house. */
public class AuctionListedEvent extends Event {

    private final UUID seller;
    private final String listingId;
    private final ItemStack item;
    private final Money price;
    private final boolean auction;

    public AuctionListedEvent(UUID seller, String listingId, ItemStack item, Money price, boolean auction) {
        this.seller = seller;
        this.listingId = listingId;
        this.item = item.copy();
        this.price = price;
        this.auction = auction;
    }

    public UUID seller() { return seller; }

    public String listingId() { return listingId; }

    public ItemStack item() { return item.copy(); }

    /** Buy-now price, or the starting bid for auctions. */
    public Money price() { return price; }

    /** True for bid auctions, false for buy-now. */
    public boolean auction() { return auction; }
}
