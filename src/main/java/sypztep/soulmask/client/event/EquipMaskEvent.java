package sypztep.soulmask.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import sypztep.soulmask.client.SoulMaskModClient;
import sypztep.soulmask.common.payload.MaskPayload;
import sypztep.soulmask.common.util.SoulMaskUtil;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class EquipMaskEvent implements ClientTickEvents.EndTick {
    private static final int DEFAULT_COOLDOWN = 20;
    private static int keypressed = DEFAULT_COOLDOWN, pressCooldown = 10;

    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.player == null) return;

        if (VizardComponentUtil.canUseMask(client.player) && SoulMaskModClient.EQUIPMASK_KEYBINDING.isPressed() && pressCooldown <= 0)
            handleMaskEquipping(client);
        else if (SoulMaskModClient.EQUIPMASK_KEYBINDING.wasPressed()) {
            flush();
            client.player.sendMessage(Text.of("You don't have ability"), true);
        }
        if (pressCooldown > 0)
            pressCooldown--;
    }

    private static void handleMaskEquipping(MinecraftClient client) {
        if (client.player == null)
            return;
        keypressed--;

        if (keypressed <= 0) {
            flush();
            MaskPayload.send();
        }
    }

    private static void flush() {
        pressCooldown = 10;
        keypressed = DEFAULT_COOLDOWN;
    }

    public static int getKeypressed() {
        return keypressed;
    }

    public static int getDefaultCooldown() {
        return DEFAULT_COOLDOWN;
    }
}
