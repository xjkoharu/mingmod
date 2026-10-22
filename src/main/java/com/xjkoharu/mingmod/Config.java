package com.xjkoharu.mingmod;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Common config of Ming Mod. Values live in {@code config/mingmod-common.toml}.
 */
public final class Config {
    private static final Pair<Common, ModConfigSpec> PAIR =
            new ModConfigSpec.Builder().configure(Common::new);

    public static final Common COMMON = PAIR.getLeft();
    public static final ModConfigSpec SPEC = PAIR.getRight();

    private Config() {
    }

    public static final class Common {
        public final ModConfigSpec.BooleanValue debugLogging;

        private Common(ModConfigSpec.Builder builder) {
            builder.comment("Debug switches").push("debug");
            debugLogging = builder
                    .comment("Print extra diagnostic messages to the log.")
                    .define("debugLogging", false);
            builder.pop();
        }
    }
}
