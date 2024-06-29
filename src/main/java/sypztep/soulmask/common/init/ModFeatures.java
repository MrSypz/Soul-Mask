package sypztep.soulmask.common.init;

import com.google.common.collect.ImmutableMap;
import sypztep.soulmask.client.data.MaskModelData;
import sypztep.soulmask.client.render.model.VastoModel;
import sypztep.soulmask.client.render.model.VizardModel;

public class ModFeatures {
    public static ImmutableMap<String, MaskModelData> MASK_DATA;

    static {
        MASK_DATA = ImmutableMap.<String, MaskModelData>builder()
                .put("hollow_1", new MaskModelData(VizardModel::new, "hollow_mask_1"))
                .put("hollow_2", new MaskModelData(VizardModel::new, "hollow_mask_2"))
                .put("hollow_3", new MaskModelData(VizardModel::new, "hollow_mask_3"))
                .put("hollow_4", new MaskModelData(VizardModel::new, "hollow_mask_4"))
                .put("hollow_5", new MaskModelData(VizardModel::new, "hollow_mask_5"))
                .put("hollow_6", new MaskModelData(VastoModel::new, "vasto_mask"))
                .build();
    }
}
