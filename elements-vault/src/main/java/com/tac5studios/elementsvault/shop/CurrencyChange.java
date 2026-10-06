package com.tac5studios.elementsvault.shop;

import com.tac5studios.elementsvault.Currency;

import java.time.Instant;

/** The server is switching from one currency to another. */
public record CurrencyChange(Currency from, Currency to, Instant when) {}
