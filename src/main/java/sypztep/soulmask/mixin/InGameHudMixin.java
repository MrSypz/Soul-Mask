package sypztep.soulmask.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sypztep.soulmask.common.init.ModEntityComponents;

@Environment(EnvType.CLIENT)
@Mixin({InGameHud.class})
public abstract class InGameHudMixin {
    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    @Final
    private static Identifier HOTBAR_OFFHAND_LEFT_TEXTURE;


    @Inject(method = {"renderHotbar"}, at = {@At("TAIL")})
    private void renderWeaponSlot(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        if (player != null) {
            if (ModEntityComponents.VIZARD.get(player).isEquipMask()) {
                int i = context.getScaledWindowWidth() / 3;
                RenderSystem.enableBlend();
                //todo: render select skill
//                context.drawGuiTexture(HOTBAR_SELECTION_TEXTURE, i - 12, context.getScaledWindowHeight() - 23 - 70, 24, 23);
                RenderSystem.defaultBlendFunc();
                for (int j = 0; j < 3; j++) {
                    context.drawGuiTexture(HOTBAR_OFFHAND_LEFT_TEXTURE, i - 90 + j * 21, context.getScaledWindowHeight() - 23, 29, 24);
                }
                RenderSystem.disableBlend();
            }
        }
    }
}
