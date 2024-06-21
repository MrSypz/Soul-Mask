package sypztep.soulmask.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import sypztep.soulmask.client.SoulMaskModClient;
import sypztep.soulmask.common.util.SoulMaskUtil;
import sypztep.soulmask.common.util.VizardUtil;

public class MaskEquip implements ClientTickEvents.EndTick {
    private final int DEFAULT_COOLDOWN = 10;
    private int pressCooldown = DEFAULT_COOLDOWN;
    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.player == null)
            return;
        if (VizardUtil.getHogyoku(client.player) > 0 && pressCooldown < 1) {
            SoulMaskUtil.handleEquipMask(client.player);
        }
        handlePressCooldown();
    }
    private void handlePressCooldown() {
        if (pressCooldown > 0 && SoulMaskModClient.EQUIPMASK_KEYBINDING.isPressed()) {
            this.pressCooldown--;
        } else if (!SoulMaskModClient.EQUIPMASK_KEYBINDING.isPressed()) {
            this.pressCooldown = DEFAULT_COOLDOWN;
        }
    }
}
