package com.tac5studios.elementsvault;

/** Where a currency's money lives. */
public enum CurrencyKind {
    /** Stored by the provider as a number (Elements: Economy digital money). */
    DIGITAL,
    /** Physical coins and notes in the inventory and inside wallets, bags and boxes. */
    ITEMS,
    /** Stored by another mod (its own API, player data or files). */
    EXTERNAL
}
