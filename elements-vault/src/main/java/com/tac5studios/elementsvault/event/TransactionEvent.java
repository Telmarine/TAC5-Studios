package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.Cause;
import com.tac5studios.elementsvault.Money;
import com.tac5studios.elementsvault.Result;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/** Fired on the NeoForge game bus around every change made through the economy service. */
public abstract class TransactionEvent extends Event {

    public enum Type { WITHDRAW, DEPOSIT, SET, TRANSFER }

    private final Type type;
    private final UUID player;
    @Nullable
    private final UUID other;
    private final Cause cause;
    protected Money amount;

    protected TransactionEvent(Type type, UUID player, @Nullable UUID other, Money amount, Cause cause) {
        this.type = type;
        this.player = player;
        this.other = other;
        this.amount = amount;
        this.cause = cause;
    }

    public Type type() { return type; }

    /** The player whose balance changes (the payer for transfers). */
    public UUID player() { return player; }

    /** The receiver for transfers, otherwise null. */
    @Nullable
    public UUID other() { return other; }

    public Money amount() { return amount; }

    public Cause cause() { return cause; }

    /** Before the change. Cancel to stop it, or change the amount (same currency). */
    public static class Pre extends TransactionEvent implements ICancellableEvent {
        public Pre(Type type, UUID player, @Nullable UUID other, Money amount, Cause cause) {
            super(type, player, other, amount, cause);
        }

        public void setAmount(Money amount) {
            if (!amount.currency().id().equals(this.amount.currency().id())) {
                throw new IllegalArgumentException("Amount must stay in " + this.amount.currency().id());
            }
            this.amount = amount;
        }
    }

    /** After the change, with the result. */
    public static class Post extends TransactionEvent {
        private final Result result;

        public Post(Type type, UUID player, @Nullable UUID other, Money amount, Cause cause, Result result) {
            super(type, player, other, amount, cause);
            this.result = result;
        }

        public Result result() { return result; }
    }
}
