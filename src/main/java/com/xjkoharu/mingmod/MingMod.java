package com.xjkoharu.mingmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

/**
 * Main entry point of Ming Mod.
 *
 * <p>The value of {@link #MODID} must stay in sync with {@code mod_id} in
 * {@code gradle.properties} (which ends up in {@code META-INF/neoforge.mods.toml}).</p>
 */
@Mod(MingMod.MODID)
public class MingMod {
    /** Namespace of every registry entry / resource this mod ships. */
    public static final String MODID = "mingmod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public MingMod(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
