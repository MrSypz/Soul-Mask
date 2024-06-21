package sypztep.soulmask.client.render.entity.render;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import sypztep.soulmask.client.render.model.HollowMaskModel;
import sypztep.soulmask.common.item.MaskItem;

public class HollowmaskRenderer extends GeoArmorRenderer<MaskItem> {
    public HollowmaskRenderer() {
        super(new HollowMaskModel());
    }
}
