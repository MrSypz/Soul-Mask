package sypztep.soulmask.client.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
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
        else if (SoulMaskModClient.EQUIPMASK_KEYBINDING.wasPressed())
            flush();
        if (pressCooldown > 0)
            pressCooldown--;
    }

    private static void handleMaskEquipping(MinecraftClient client) {
        if (client.player == null)
            return;
        //Handle particle
//        if (minecraft.player.age % 5 == 0) {
////            SoulMaskUtil.addChargeParticle(minecraft.player);
////            MaskPacket.send(3);
//        }
        keypressed--;

        if (keypressed <= 0) {
            flush();
            MaskPayload.send();
//            SoulMaskUtil.addUseMaskParticle(minecraft.player);
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
