package com.tac5studios.elementsvault.event;

import com.tac5studios.elementsvault.backend.CurrencyBackend;
import net.neoforged.bus.api.Event;

import java.util.function.Consumer;

/**
 * Fired on the NeoForge game bus when the server starts, before shops load.
 * Add your own money system here. Ignored when the owner turned off api.external_backends.
 */
public class RegisterCurrencyBackendsEvent extends Event {

    private final Consumer<CurrencyBackend> sink;

    public RegisterCurrencyBackendsEvent(Consumer<CurrencyBackend> sink) {
        this.sink = sink;
    }

    public void register(CurrencyBackend backend) {
        sink.accept(backend);
    }
}
