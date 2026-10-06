# Elements: Vault

Shared economy API for TAC5 Studios mods (NeoForge 1.21.1). Holds no money itself: a provider (Elements: Economy) registers the service, and every other mod uses it.

```java
EconomyAPI.get().ifPresent(eco -> {
    Money price = eco.primaryCurrency().of(250);
    Result r = eco.withdraw(player.getUUID(), price, Cause.plugin("mymod"));
});
```

- `EconomyService` — balances, transfers, dry runs, conversion, coin items.
- `backend/CurrencyBackend` — plug in a money system.
- `shop/ShopBridge` — plug in a shop or auction mod.
- `event/*` — transactions, balance changes, currency switch, shop and auction events (game bus).

Only currencies that come with a mod are currencies. Vanilla items count only after the owner lists them and an admin confirms.

## Building
`gradlew build`, then `gradlew publishToMavenLocal` so Elements: Economy can bundle it.
Other mods: `compileOnly "com.tac5studios:elements_vault:1.0.0"` and check `EconomyAPI.isAvailable()`.
