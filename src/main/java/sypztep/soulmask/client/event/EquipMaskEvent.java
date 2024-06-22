package sypztep.soulmask.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import sypztep.soulmask.client.SoulMaskModClient;
import sypztep.soulmask.common.util.SoulMaskUtil;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class EquipMaskEvent implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.player == null) return;
        if (VizardComponentUtil.canUseMask(client.player) && SoulMaskModClient.EQUIPMASK_KEYBINDING.isPressed())
            SoulMaskUtil.handleEquipMask(client.player);
    }
}
