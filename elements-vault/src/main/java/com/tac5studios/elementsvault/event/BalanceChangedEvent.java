package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.Money;
import net.neoforged.bus.api.Event;

import java.util.UUID;

/** A balance changed, through the service or noticed from outside (another mod, coins picked up). */
public class BalanceChangedEvent extends Event {

    private final UUID player;
    private final Money before;
    private final Money after;

    public BalanceChangedEvent(UUID player, Money before, Money after) {
        this.player = player;
        this.before = before;
        this.after = after;
    }

    public UUID player() { return player; }

    public Money before() { return before; }

    public Money after() { return after; }
}
