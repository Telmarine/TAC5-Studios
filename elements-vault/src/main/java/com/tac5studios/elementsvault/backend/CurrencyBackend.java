package com.tac5studios.elementsvault.backend;

import com.tac5studios.elementsvault.Capability;
import com.tac5studios.elementsvault.Cause;
import com.tac5studios.elementsvault.Currency;
import com.tac5studios.elementsvault.Holding;
import com.tac5studios.elementsvault.Result;
import net.minecraft.resources.ResourceLocation;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * A money system: where one currency's balances live (digital, Aiycoin coins, CobbleDollars, ...).
 * The provider picks the backend for a currency and handles events, logs and limits around it.
 * A backend only reads and writes; it never posts events itself.
 *
 * Register one with {@link com.tac5studios.elementsvault.event.RegisterCurrencyBackendsEvent}.
 */
public interface CurrencyBackend {

    /** e.g. elements_economy:digital, elements_economy:aiycoin */
    ResourceLocation id();

    Currency currency();

    default Set<Capability> capabilities() {
        return currency().capabilities();
    }

    /** True when the owning mod is installed and its code was found. */
    boolean isAvailable();

    /** Balance in the smallest unit. Zero when the player has none. */
    BigInteger balance(UUID player);

    /** Takes money. Must fail without changing anything when the player doesn't have enough. */
    Result withdraw(UUID player, BigInteger amount, Cause cause);

    /** Adds money. */
    Result deposit(UUID player, BigInteger amount, Cause cause);

    /** Sets the balance. Default: deposit or withdraw the difference. */
    default Result set(UUID player, BigInteger amount, Cause cause) {
        BigInteger now = balance(player);
        int cmp = amount.compareTo(now);
        if (cmp > 0) return deposit(player, amount.subtract(now), cause);
        if (cmp < 0) return withdraw(player, now.subtract(amount), cause);
        return Result.ok(currency().of(0), currency().of(now), currency().of(now));
    }

    /** Can this player receive this much right now? (Room for coins, offline support ...) */
    default boolean canDeposit(UUID player, BigInteger amount) {
        return true;
    }

    /** Richest players, highest first. Only for backends with {@link Capability#LIST_ALL}. */
    default List<Holding> top(int limit) {
        return List.of();
    }
}
