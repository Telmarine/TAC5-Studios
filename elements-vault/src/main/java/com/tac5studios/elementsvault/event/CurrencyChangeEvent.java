package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.shop.ConversionPlan;
import com.tac5studios.elementsvault.shop.CurrencyChange;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/** The server is switching currency. Fired once for the real switch, never for the preview. */
public abstract class CurrencyChangeEvent extends Event {

    private final CurrencyChange change;
    private final ConversionPlan plan;

    protected CurrencyChangeEvent(CurrencyChange change, ConversionPlan plan) {
        this.change = change;
        this.plan = plan;
    }

    public CurrencyChange change() { return change; }

    public ConversionPlan plan() { return plan; }

    /** Before anything converts. Cancel to stop the switch. */
    public static class Pre extends CurrencyChangeEvent implements ICancellableEvent {
        public Pre(CurrencyChange change, ConversionPlan plan) {
            super(change, plan);
        }
    }

    /** After everything converted. */
    public static class Post extends CurrencyChangeEvent {
        public Post(CurrencyChange change, ConversionPlan plan) {
            super(change, plan);
        }
    }
}
