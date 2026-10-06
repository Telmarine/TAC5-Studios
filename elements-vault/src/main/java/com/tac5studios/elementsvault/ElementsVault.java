package com.tac5studios.elementsvault;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Elements: Vault — the shared economy API for NeoForge mods.
 * It holds no money itself. A provider (Elements: Economy) registers an {@link EconomyService},
 * and every other mod talks to that service through {@link EconomyAPI}.
 */
@Mod(ElementsVault.MOD_ID)
public class ElementsVault {

    public static final String MOD_ID = "elements_vault";
    public static final Logger LOGGER = LoggerFactory.getLogger("ElementsVault");

    public ElementsVault() {
        LOGGER.info("[Vault] Elements: Vault API loaded.");
    }
}
