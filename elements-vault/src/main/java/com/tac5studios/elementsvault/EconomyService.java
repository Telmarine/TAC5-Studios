package com.tac5studios.elementsvault;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Everything other mods can do with money. Get it from {@link EconomyAPI#get()}.
 *
 * Call the sync methods on the server thread. For offline players, or currencies marked
 * {@link Capability#ASYNC_ONLY}, use the async versions.
 *
 * Rule: only currencies that come with a mod are currencies. Vanilla items count only after
 * the server owner lists them in currency_values.toml and an admin confirms.
 */
public interface EconomyService {

    /** Name of the mod providing this service, for logs. */
    String providerName();

    // ---------- currencies ----------

    /** The currency shops use by default (the detected currency mod, or digital money). */
    Currency primaryCurrency();

    /** Every currency shops may accept. One unless the owner turned on multiple currencies. */
    List<Currency> activeCurrencies();

    /** Every currency that is installed and recognised. */
    Collection<Currency> currencies();

    Optional<Currency> currency(ResourceLocation id);

    // ---------- reading ----------

    /** The player's balance. Zero when they have none. */
    Money balance(UUID player, Currency currency);

    default boolean has(UUID player, Money money) {
        return balance(player, money.currency()).compareTo(money) >= 0;
    }

    /** Richest players, highest first. Empty when the currency can't list everyone. */
    List<Holding> top(Currency currency, int limit);

    // ---------- dry runs (nothing changes) ----------

    Result canWithdraw(UUID player, Money money);

    Result canDeposit(UUID player, Money money);

    // ---------- changes ----------

    Result withdraw(UUID player, Money money, Cause cause);

    Result deposit(UUID player, Money money, Cause cause);

    Result set(UUID player, Money money, Cause cause);

    /** Moves money from one player to another. All or nothing. */
    Result transfer(UUID from, UUID to, Money money, Cause cause);

    // ---------- converting ----------

    /** The same value in another currency, using the owner's rates. Empty when there is no rate. */
    Optional<Money> convert(Money money, Currency target);

    /** Coin stacks for an amount of an item currency, fewest coins first. Empty for other currencies. */
    List<ItemStack> toItems(Money money);

    /** The value of these stacks in a currency. Items that aren't that currency are ignored. */
    Money fromItems(Collection<ItemStack> stacks, Currency currency);

    /** True when this item is a recognised currency item. */
    boolean isCurrencyItem(ItemStack stack);

    // ---------- async (offline players, async-only currencies) ----------

    default CompletableFuture<Money> balanceAsync(UUID player, Currency currency) {
        return CompletableFuture.completedFuture(balance(player, currency));
    }

    default CompletableFuture<Result> withdrawAsync(UUID player, Money money, Cause cause) {
        return CompletableFuture.completedFuture(withdraw(player, money, cause));
    }

    default CompletableFuture<Result> depositAsync(UUID player, Money money, Cause cause) {
        return CompletableFuture.completedFuture(deposit(player, money, cause));
    }

    default CompletableFuture<Result> setAsync(UUID player, Money money, Cause cause) {
        return CompletableFuture.completedFuture(set(player, money, cause));
    }

    default CompletableFuture<Result> transferAsync(UUID from, UUID to, Money money, Cause cause) {
        return CompletableFuture.completedFuture(transfer(from, to, money, cause));
    }
}
