package com.tac5studios.elementsvault.shop;

import com.tac5studios.elementsvault.Money;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

/**
 * Tools a shop bridge uses during a currency switch-over.
 * In a preview, bridges only count what they would change and report it.
 */
public interface ConversionPlan {

    /** True for the preview: count, change nothing. */
    boolean preview();

    /** A money price in the new currency. Empty when there is no rate. */
    Optional<Money> convert(Money price);

    /** True when this item is a coin of the old currency. */
    boolean isOldCurrencyItem(ItemStack stack);

    /**
     * New-currency coins worth the same as this stack of old coins, fewest coins first.
     * Empty list when the stack isn't old currency (vanilla items and other items are left alone).
     */
    List<ItemStack> swap(ItemStack oldCoins);

    /**
     * Tell the provider what this bridge changed (or would change, in a preview).
     * @param changed prices converted
     * @param skipped prices left alone (vanilla items, no rate)
     */
    void report(ResourceLocation bridge, int changed, int skipped);
}
