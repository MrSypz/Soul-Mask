package sypztep.soulmask.client.data;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.client.render.model.MaskModel;

import java.util.function.Function;


public class MaskModelData {
	private final Function<EntityRendererFactory.Context, MaskModel> model;
	private final Identifier texture;

	public MaskModelData(Function<EntityRendererFactory.Context, MaskModel> model, String textureName) {
		this.model = model;
		this.texture = SoulMaskMod.id("textures/feature/" + textureName + ".png");
	}

	public MaskModel createModel(EntityRendererFactory.Context ctx) {
		return model.apply(ctx);
	}

	public Identifier getTexture() {
		return texture;
	}
}
