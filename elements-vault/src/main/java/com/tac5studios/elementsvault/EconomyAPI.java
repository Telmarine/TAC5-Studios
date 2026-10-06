package com.tac5studios.elementsvault;

import java.util.Optional;

/**
 * The entry point. Other mods call {@code EconomyAPI.get()} and use the service if one is there.
 * <pre>
 * EconomyAPI.get().ifPresent(eco -> eco.withdraw(player, eco.primaryCurrency().of(250), Cause.plugin("mymod")));
 * </pre>
 * The service exists only while a server with a provider (Elements: Economy) is running.
 */
public final class EconomyAPI {

    private static volatile EconomyService service;

    private EconomyAPI() {}

    /** The running economy, or empty when no provider is running. */
    public static Optional<EconomyService> get() {
        return Optional.ofNullable(service);
    }

    /** True when a provider is running. */
    public static boolean isAvailable() {
        return service != null;
    }

    /** Called by the provider when the server starts. Only one provider at a time. */
    public static synchronized void provide(EconomyService provider) {
        if (service != null && service != provider) {
            ElementsVault.LOGGER.warn("[Vault] {} replaced the economy provider {}.", provider.providerName(), service.providerName());
        }
        service = provider;
        ElementsVault.LOGGER.info("[Vault] Economy provider: {}", provider.providerName());
    }

    /** Called by the provider when the server stops. */
    public static synchronized void clear(EconomyService provider) {
        if (service == provider) {
            service = null;
        }
    }
}
