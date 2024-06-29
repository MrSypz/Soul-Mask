package sypztep.soulmask.client.render.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import sypztep.soulmask.SoulMaskMod;

public class VizardModel extends MaskModel{
    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(SoulMaskMod.id("hollow_mask_model"), "main");

    public VizardModel(EntityRendererFactory.Context ctx) {
        super(ctx, MODEL_LAYER);
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData partdefinition = modelData.getRoot();
        ModelPartData Head = partdefinition.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData mask = Head.addChild("mask", ModelPartBuilder.create().uv(0, 1).cuboid(-4.0F, -33.0F, -5.0F, 8.0F, 11.0F, 0.0F, new Dilation(0.0F))
                .uv(-1, 0).cuboid(-4.0F, -33.0F, -5.0F, 8.0F, 0.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-4.0F, -33.0F, -5.5F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 20).cuboid(-4.0F, -25.0F, -5.5F, 8.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 1.0F));

        mask.addChild("leftmask_r1", ModelPartBuilder.create().uv(16, -2).cuboid(-4.0F, -8.0F, 2.0F, 0.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 5).cuboid(4.0F, -8.0F, 2.0F, 0.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -24.0F, -1.0F, 3.1416F, 0.0F, -3.1416F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        head.render(matrices, vertices, light, overlay, color);
    }
}
