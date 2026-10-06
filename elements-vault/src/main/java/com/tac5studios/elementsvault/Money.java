package com.tac5studios.elementsvault;

import java.math.BigInteger;
import java.util.Objects;

/**
 * An amount of one currency, as a whole number in the currency's smallest unit.
 * No decimals and no rounding: 12.50 Coins with 2 decimals is stored as 1250.
 */
public record Money(Currency currency, BigInteger amount) implements Comparable<Money> {

    public Money {
        Objects.requireNonNull(currency, "currency");
        Objects.requireNonNull(amount, "amount");
    }

    public Money add(Money other) {
        same(other);
        return new Money(currency, amount.add(other.amount));
    }

    public Money subtract(Money other) {
        same(other);
        return new Money(currency, amount.subtract(other.amount));
    }

    public Money multiply(long times) {
        return new Money(currency, amount.multiply(BigInteger.valueOf(times)));
    }

    public boolean isZero() {
        return amount.signum() == 0;
    }

    public boolean isNegative() {
        return amount.signum() < 0;
    }

    @Override
    public int compareTo(Money other) {
        same(other);
        return amount.compareTo(other.amount);
    }

    private void same(Money other) {
        if (!currency.id().equals(other.currency.id())) {
            throw new IllegalArgumentException("Different currencies: " + currency.id() + " and " + other.currency.id());
        }
    }

    @Override
    public String toString() {
        return amount + " " + currency.id();
    }
}
