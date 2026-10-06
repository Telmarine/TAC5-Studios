package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.shop.ShopBridge;
import net.neoforged.bus.api.Event;

import java.util.function.Consumer;

/**
 * Fired on the NeoForge game bus when the server starts, before shops load.
 * Add your shop or auction mod here. Ignored when the owner turned off api.external_bridges.
 */
public class RegisterShopBridgesEvent extends Event {

    private final Consumer<ShopBridge> sink;

    public RegisterShopBridgesEvent(Consumer<ShopBridge> sink) {
        this.sink = sink;
    }

    public void register(ShopBridge bridge) {
        sink.accept(bridge);
    }
}
