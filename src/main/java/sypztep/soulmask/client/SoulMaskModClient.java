package sypztep.soulmask.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.client.event.EquipMaskEvent;
import sypztep.soulmask.client.event.MaskRendererEvent;
import sypztep.soulmask.client.event.hud.EnergyHudEvent;
import sypztep.soulmask.client.render.model.VizardModel;

public class SoulMaskModClient implements ClientModInitializer {
    public static final KeyBinding EQUIPMASK_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + SoulMaskMod.MODID + ".equipmask", GLFW.GLFW_KEY_V, "key.categories." + SoulMaskMod.MODID));
    public static final KeyBinding DEBUG_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding("key." + SoulMaskMod.MODID + ".debug", GLFW.GLFW_KEY_Z, "key.categories." + SoulMaskMod.MODID));


    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(new EquipMaskEvent());
        HudRenderCallback.EVENT.register(new EnergyHudEvent());

        EntityModelLayerRegistry.registerModelLayer(VizardModel.MODEL_LAYER, VizardModel::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(new MaskRendererEvent());
    }
}
