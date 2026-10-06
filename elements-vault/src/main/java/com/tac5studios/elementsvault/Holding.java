package com.tac5studios.elementsvault;

import java.util.UUID;

/** One player's balance, used by top lists. */
public record Holding(UUID player, Money money) {}
