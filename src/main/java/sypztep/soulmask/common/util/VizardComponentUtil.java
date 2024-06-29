package sypztep.soulmask.common.util;

import net.minecraft.entity.player.PlayerEntity;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.component.VizardEnergyComponent;

/**
 * Utility for VizardComponent
 */
public class VizardComponentUtil {
    public static int getHogyoku(PlayerEntity player) {
        return VizardComponent.getComponent(player).getHogyoku();
    }

    public static boolean canUseMask(PlayerEntity player) {
        return getHogyoku(player) > 0;
    }

    public static boolean isEquipMask(PlayerEntity player) {
        return VizardComponent.getComponent(player).isEquipMask();
    }

    public static void setEquipMask(PlayerEntity player, boolean bl) {
        VizardComponent.getComponent(player).setEquipMask(bl);
    }

    public static int getEnergy(PlayerEntity player) {
        return VizardEnergyComponent.getComponent(player).getEnergy();
    }
}
