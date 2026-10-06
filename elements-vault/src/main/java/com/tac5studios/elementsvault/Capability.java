package com.tac5studios.elementsvault;

/** What a currency can do. Check before calling, or use the dry runs. */
public enum Capability {
    /** Balances can be read while the player is offline. */
    OFFLINE_READ,
    /** Balances can be changed while the player is offline. */
    OFFLINE_WRITE,
    /** Accounts can be shared between players. */
    SHARED_ACCOUNTS,
    /** Must be used through the async methods. */
    ASYNC_ONLY,
    /** The balance is the coins the player carries. */
    ITEM_BACKED,
    /** The currency can list every balance (for /baltop). */
    LIST_ALL
}
