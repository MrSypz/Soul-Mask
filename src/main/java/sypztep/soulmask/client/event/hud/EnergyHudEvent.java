package sypztep.soulmask.client.event.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.client.event.EquipMaskEvent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class EnergyHudEvent implements HudRenderCallback {
    private static final Identifier ENERGY = SoulMaskMod.id("textures/gui/mask.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        int windowWidth = drawContext.getScaledWindowWidth();
        int windowHeight = drawContext.getScaledWindowHeight();
        ModEntityComponents.VIZARD_ENERGY.maybeGet(MinecraftClient.getInstance().getCameraEntity()).ifPresent(vizardComponent -> {
            if (vizardComponent.getEnergy() <= 100) {
                renderCooldownBar(drawContext, windowWidth / 2 - 16, windowHeight / 2 + 40, vizardComponent.getEnergy(), 100);
            }
        });
        if (VizardComponentUtil.canUseMask(minecraft.player)) {
            if (EquipMaskEvent.getKeypressed() < 20 )
                renderCooldownBar(drawContext, windowWidth / 2 - 16, windowHeight / 2 + 18, EquipMaskEvent.getKeypressed(), EquipMaskEvent.getDefaultCooldown());
        }
    }

    private void renderCooldownBar(DrawContext drawContext, int x, int y, int max, int current) {
        RenderSystem.enableBlend();
        drawContext.drawTexture(ENERGY, x, y, 0, 5, 32, 5, 32, 10);
        if (max < current)
            drawContext.drawTexture(ENERGY, x, y, 0, 0, (int) (33 - (max / (float) current) * 32), 5, 32, 10);
        RenderSystem.disableBlend();
    }
}
