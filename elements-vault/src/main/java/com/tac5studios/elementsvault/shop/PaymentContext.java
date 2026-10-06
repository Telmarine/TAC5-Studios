package com.tac5studios.elementsvault.shop;

import com.tac5studios.elementsvault.Cause;
import com.tac5studios.elementsvault.Money;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * A payment a money-priced shop is about to make in its own money system.
 * A bridge that handles it routes the payment through the economy service instead.
 *
 * @param payee the shop owner, or null for server shops
 */
public record PaymentContext(UUID payer, @Nullable UUID payee, Money price, Cause cause) {}
