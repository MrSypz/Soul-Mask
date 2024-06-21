package sypztep.soulmask.common.util;

import net.minecraft.entity.player.PlayerEntity;
import sypztep.soulmask.common.component.VizardComponent;

public class VizardUtil {
    public static int getHogyoku(PlayerEntity player) {
        return VizardComponent.getVizard(player).getHogyoku();
    }
}
