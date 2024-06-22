package sypztep.soulmask.client.event.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class HudRendere implements HudRenderCallback {
    private static final Identifier EQUIPMASKCD_TEXTURE = SoulMaskMod.id("textures/gui/mask.png");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        int windowWidth = drawContext.getScaledWindowWidth();
        int windowHeight = drawContext.getScaledWindowHeight();
//        int u = VizardComponentUtil.getEnergy(minecraft.player);
        ModEntityComponents.VIZARD_ENERGY.maybeGet(MinecraftClient.getInstance().getCameraEntity()).ifPresent(vizardComponent -> {
            if (vizardComponent.getEnergy() <= 100) {
                renderCooldownBar(drawContext, windowWidth / 2 - 16, windowHeight / 2 + 18, vizardComponent.getEnergy(), 100);

            }
        });
//        if (VizardComponentUtil.canUseMask(minecraft.player)) {
        //            else if (SoulMaskUtil.hasEquippedMask(minecraft.player) && MaskHandleTick.getUnmaskCooldown() < 20)
//                renderCooldownBar(drawContext, windowWidth / 2 - 16, windowHeight / 2 + 18, MaskHandleTick.getUnmaskCooldown(), MaskHandleTick.getLastunmaskcd());

//        }
    }

    private void renderCooldownBar(DrawContext drawContext, int x, int y, int max, int current) {
        RenderSystem.enableBlend();
        drawContext.drawTexture(EQUIPMASKCD_TEXTURE, x, y, 0, 5, 32, 5, 32, 10);
        if (max < current)
            drawContext.drawTexture(EQUIPMASKCD_TEXTURE, x, y, 0, 0, (int) (33 - (max / (float) current) * 32), 5, 32, 10);
        RenderSystem.disableBlend();
    }

}
