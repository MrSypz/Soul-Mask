package sypztep.soulmask.client.render.entity.render;

import software.bernie.geckolib.renderer.GeoArmorRenderer;
import sypztep.soulmask.client.render.model.VastoMaskModel;
import sypztep.soulmask.common.item.MaskItem;

public class VastomaskRenderer extends GeoArmorRenderer<MaskItem> {
    public VastomaskRenderer() {
        super(new VastoMaskModel());
    }
}
