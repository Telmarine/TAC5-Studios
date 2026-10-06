package com.tac5studios.elementsvault.shop;

/** How a shop mod stores its prices. */
public enum ShopKind {
    /** Prices are items (a coin stack, a price slot). Only mod-currency items are touched. */
    ITEM_PRICED,
    /** Prices are numbers in the shop's own money system. */
    MONEY_PRICED
}
