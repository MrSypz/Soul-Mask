package sypztep.soulmask.common.util;

import net.minecraft.entity.player.PlayerEntity;
import sypztep.soulmask.common.component.VizardComponent;

/**
 * Utility for VizardComponent
 */
public class VizardComponentUtil {
    public static int getHogyoku(PlayerEntity player) {
        return VizardComponent.getVizard(player).getHogyoku();
    }
    public static boolean canUseMask(PlayerEntity player) {
        return getHogyoku(player) > 0;
    }
}
