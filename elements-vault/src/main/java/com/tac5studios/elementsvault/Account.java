package com.tac5studios.elementsvault;

import java.util.UUID;

/**
 * A place money is kept. Every player has a default account per currency.
 * Named accounts (shared, business) only exist for currencies with {@link Capability#SHARED_ACCOUNTS}.
 */
public record Account(UUID owner, String name, Currency currency) {

    public static final String DEFAULT = "default";

    public static Account of(UUID owner, Currency currency) {
        return new Account(owner, DEFAULT, currency);
    }

    public boolean isDefault() {
        return DEFAULT.equals(name);
    }
}
