package sypztep.soulmask.client.render.model;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.init.ModItems;
import sypztep.soulmask.common.item.MaskItem;


public class VastoMaskModel extends GeoModel<MaskItem>{
    @Override
    public Identifier getModelResource(MaskItem animatable) {
        ItemStack stack = new ItemStack(animatable);
        if (stack.isOf(ModItems.VASTO_MASK))
            return SoulMaskMod.id( "geo/vastomask.geo.json");
        return null;
    }
    @Override
    public Identifier getTextureResource(MaskItem animatable) {
        return SoulMaskMod.id( "textures/armor/vasto_mask_armor.png");
    }

    @Override
    public Identifier getAnimationResource(MaskItem animatable) {
        return null;
    }
}
