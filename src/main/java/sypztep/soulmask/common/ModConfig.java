package sypztep.soulmask.common;

import eu.midnightdust.lib.config.MidnightConfig;
import sypztep.soulmask.SoulMaskMod;


public class ModConfig extends MidnightConfig {
    public static boolean debug = false;
    static {
        MidnightConfig.init(SoulMaskMod.MODID, ModConfig.class);
    }
}
