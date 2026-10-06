package com.tac5studios.elementsvault.shop;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Connects another mod's shops or auction house to the server's currency.
 *
 * Rules every bridge follows:
 *  - Item-priced shops are only touched when the price item is a recognised mod currency.
 *  - Item prices are never turned into digital money.
 *  - Vanilla-item prices are left alone.
 *
 * Register one with {@link com.tac5studios.elementsvault.event.RegisterShopBridgesEvent}.
 */
public interface ShopBridge {

    /** e.g. elements_economy:spuds_shops */
    ResourceLocation id();

    /** The shop mod's id, e.g. "spuds_shops". */
    String modId();

    ShopKind kind();

    /** True when the shop mod is installed and its code was found. */
    boolean isAvailable();

    /** The server switched currency (or is previewing it). Convert or count stored prices. */
    void onCurrencyChanged(CurrencyChange change, ConversionPlan plan);

    /** A shop block loaded after a switch-over. Convert it now if it was missed. */
    default void onBlockEntityLoad(BlockEntity blockEntity) {}

    /** Money-priced shops only. Return true when the bridge handled the payment. */
    default boolean interceptPayment(PaymentContext payment) {
        return false;
    }
}
