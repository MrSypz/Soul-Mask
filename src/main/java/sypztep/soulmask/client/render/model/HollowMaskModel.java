package sypztep.soulmask.client.render.model;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.init.ModItems;
import sypztep.soulmask.common.item.MaskItem;

public class HollowMaskModel extends GeoModel<MaskItem> {
    @Override
    public Identifier getModelResource(MaskItem animatable) {
//        ItemStack stack = new ItemStack(animatable);
//        if (stack.isOf(ModItems))
//            SoulMaskMod.id( "geo/vastomask.geo.json");
        return SoulMaskMod.id("geo/hollowmask.geo.json");
    }
    @Override
    public Identifier getTextureResource(MaskItem item) {
        ItemStack stack = new ItemStack(item);
        if (stack.isOf(ModItems.HOLLOW_MASK_TIER1))
            return SoulMaskMod.id("textures/armor/hollow_mask1_armor.png");
         else if (stack.isOf(ModItems.HOLLOW_MASK_TIER2))
            return SoulMaskMod.id("textures/armor/hollow_mask2_armor.png");
        else if (stack.isOf(ModItems.HOLLOW_MASK_TIER3))
            return SoulMaskMod.id("textures/armor/hollow_mask3_armor.png");
        else if (stack.isOf(ModItems.HOLLOW_MASK_TIER4))
            return SoulMaskMod.id("textures/armor/hollow_mask4_armor.png");
         else
            return SoulMaskMod.id("textures/armor/half_hollow_mask_armor.png");
//        return SoulMaskMod.id( "textures/armor/vasto_mask_armor.png");
    }

    @Override
    public Identifier getAnimationResource(MaskItem animatable) {
        return null;
    }
}
