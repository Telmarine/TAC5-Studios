package com.tac5studios.elementsvault;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

/**
 * A kind of money that comes with a mod: Elements: Economy digital money, or a detected currency mod.
 * Two currencies are the same when their ids match. Implementations must use the id for equals and hashCode.
 */
public interface Currency {

    /** e.g. elements_economy:digital, aiycoin:coins, cobbledollars:cobbledollars */
    ResourceLocation id();

    /** Display name, e.g. "Coins". */
    Component name();

    /** Short symbol, e.g. "$". Empty when the currency has none. */
    String symbol();

    /** Decimal places shown. Amounts are always stored as whole numbers in the smallest unit. */
    int decimals();

    CurrencyKind kind();

    /** Mod that owns the money, e.g. "aiycoin". */
    String sourceMod();

    Set<Capability> capabilities();

    default boolean can(Capability capability) {
        return capabilities().contains(capability);
    }

    /** Readable amount, e.g. "1,250 Coins" or "2 Gold Coin, 40 Silver Coin". */
    Component format(BigInteger amount);

    default Component format(Money money) {
        return format(money.amount());
    }

    /** Reads a typed amount ("1250", "12.50", "1.2k"). Empty when it isn't a valid amount. */
    Optional<BigInteger> parse(String text);

    /** Item shown for this currency in menus. */
    ItemStack icon();

    default Money of(long amount) {
        return new Money(this, BigInteger.valueOf(amount));
    }

    default Money of(BigInteger amount) {
        return new Money(this, amount);
    }

    default Money zero() {
        return new Money(this, BigInteger.ZERO);
    }
}
