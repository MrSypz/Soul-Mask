package sypztep.soulmask.client;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import org.lwjgl.glfw.GLFW;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.client.event.EquipMaskEvent;
import sypztep.soulmask.client.event.hud.EnergyHudEvent;
import sypztep.soulmask.client.data.MaskModelData;
import sypztep.soulmask.client.render.feature.MaskFeatureRenderer;
import sypztep.soulmask.client.render.model.VizardModel;

public class SoulMaskModClient implements ClientModInitializer {
    public static final KeyBinding EQUIPMASK_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + SoulMaskMod.MODID + ".equipmask", GLFW.GLFW_KEY_V, "key.categories." + SoulMaskMod.MODID));
    public static final KeyBinding DEBUG_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + SoulMaskMod.MODID + ".debug", GLFW.GLFW_KEY_Z, "key.categories." + SoulMaskMod.MODID));
    public static ImmutableMap<String, MaskModelData> MASK_DATA;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(new EquipMaskEvent());
        HudRenderCallback.EVENT.register(new EnergyHudEvent());

        EntityModelLayerRegistry.registerModelLayer(VizardModel.MODEL_LAYER, VizardModel::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER) {
                @SuppressWarnings("unchecked") var playerRenderer = (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer;
                registrationHelper.register(new MaskFeatureRenderer(playerRenderer, context));
            }
        });
        MASK_DATA = ImmutableMap.<String, MaskModelData>builder()
                .put("hollow_1", new MaskModelData(VizardModel::new, "hollow_mask_1"))
                .put("hollow_2", new MaskModelData(VizardModel::new, "hollow_mask_2"))
                .put("hollow_3", new MaskModelData(VizardModel::new, "hollow_mask_3"))
                .put("hollow_4", new MaskModelData(VizardModel::new, "hollow_mask_4"))
                .put("hollow_5", new MaskModelData(VizardModel::new, "hollow_mask_5"))
                .build();
    }
}
