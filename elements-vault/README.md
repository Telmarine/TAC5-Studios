# Elements: Vault

A shared economy API for NeoForge 1.21.1. Vault holds no money itself. A provider (Elements: Economy) registers the service, and any other mod can use it to read balances, take or pay money, and hook in its own shops or currency.

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

Only currencies that come with a mod are currencies. Vanilla items count only after the server owner lists them and an admin confirms.

## Using it in your mod
Vault is on CurseForge, so you can pull it with CurseMaven:

```groovy
repositories {
    maven {
        url = "https://cursemaven.com"
        content { includeGroup "curse.maven" }
    }
}

dependencies {
    compileOnly "curse.maven:elements-vault-1730724:9083690"
}
```

Always check `EconomyAPI.isAvailable()` (or use `EconomyAPI.get()`) before calling anything, since the service only exists while a provider is running.

## Building
`gradlew build`, then `gradlew publishToMavenLocal` so Elements: Economy can bundle it as `com.tac5studios:elements_vault`.
