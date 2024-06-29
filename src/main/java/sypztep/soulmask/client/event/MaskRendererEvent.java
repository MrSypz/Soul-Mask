package sypztep.soulmask.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import sypztep.soulmask.client.render.feature.MaskFeatureRenderer;

public class MaskRendererEvent implements LivingEntityFeatureRendererRegistrationCallback {

    @Override
    public void registerRenderers(EntityType<? extends LivingEntity> entityType, LivingEntityRenderer<?, ?> entityRenderer, RegistrationHelper registrationHelper, EntityRendererFactory.Context context) {
        if (entityType == EntityType.PLAYER) {
            @SuppressWarnings("unchecked") var playerRenderer = (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) entityRenderer;
            registrationHelper.register(new MaskFeatureRenderer(playerRenderer, context));
        }
    }
}
