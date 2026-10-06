package com.tac5studios.elementsvault;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

/**
 * What happened. Always check {@link #success()}.
 *
 * @param before  balance before (null when unknown or for dry runs)
 * @param after   balance after (null when unknown or for dry runs)
 * @param message readable reason for players, empty on success
 */
public record Result(boolean success, Reason reason, Money amount,
                     @Nullable Money before, @Nullable Money after, Component message) {

    public enum Reason {
        OK,
        INSUFFICIENT_FUNDS,
        ACCOUNT_MISSING,
        OFFLINE_NOT_SUPPORTED,
        LIMIT,
        CANCELLED,
        BACKEND_ERROR,
        NOT_SUPPORTED,
        INVALID_AMOUNT
    }

    public static Result ok(Money amount, @Nullable Money before, @Nullable Money after) {
        return new Result(true, Reason.OK, amount, before, after, Component.empty());
    }

    /** A dry run that would succeed. */
    public static Result allowed(Money amount) {
        return new Result(true, Reason.OK, amount, null, null, Component.empty());
    }

    public static Result fail(Reason reason, Money amount, Component message) {
        return new Result(false, reason, amount, null, null, message);
    }

    public static Result fail(Reason reason, Money amount) {
        return fail(reason, amount, Component.literal(reason.name()));
    }
}
