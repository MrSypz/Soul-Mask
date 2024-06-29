package sypztep.soulmask.client.render.feature;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import sypztep.soulmask.client.SoulMaskModClient;
import sypztep.soulmask.client.render.model.MaskModel;

import java.util.Map;
import java.util.stream.Collectors;

public class MaskFeatureRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    private final Map<String, ResolvedOverheadData> models;

    public MaskFeatureRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext, EntityRendererFactory.Context loader) {
        super(featureRendererContext);
        this.models = SoulMaskModClient.MASK_DATA.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, data -> new ResolvedOverheadData(data.getValue().getTexture(), data.getValue().createModel(loader))));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
//		if (cosmeticData != null && !entity.isInvisible()) {
//			String playerOverhead = cosmeticData.getOverhead();
//			if (playerOverhead != null) {
        ResolvedOverheadData resolvedOverheadData = this.models.get("hollow_4");
        if (resolvedOverheadData != null) {
            Identifier texture = resolvedOverheadData.texture();
            MaskModel model = resolvedOverheadData.model();

            model.head.pivotX = this.getContextModel().head.pivotX;
            model.head.pivotY = this.getContextModel().head.pivotY;
            model.head.pitch = this.getContextModel().head.pitch;
            model.head.yaw = this.getContextModel().head.yaw;

            model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorCutoutNoCull(texture)), light, OverlayTexture.DEFAULT_UV);
        }
    }

    private record ResolvedOverheadData(Identifier texture, MaskModel model) {
    }
}
