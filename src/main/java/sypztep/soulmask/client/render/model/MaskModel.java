package sypztep.soulmask.client.render.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class MaskModel extends Model {
	public final ModelPart head;

	public MaskModel(EntityRendererFactory.Context ctx, EntityModelLayer entityModelLayer) {
		super(RenderLayer::getArmorCutoutNoCull);
		this.head = ctx.getPart(entityModelLayer).getChild("head");
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		this.head.render(matrices, vertices, light, overlay);
	}
}
