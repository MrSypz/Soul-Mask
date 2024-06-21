package sypztep.soulmask;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import sypztep.soulmask.common.init.ModItems;
import sypztep.soulmask.common.payload.HogyokuPayload;

public class SoulMaskMod implements ModInitializer {
    public static final String MODID = "soulmask";
//    public static final Logger LOGGER = LoggearFactory.getLogger(MODID);
    public static Identifier id(String id) {
        return Identifier.of(MODID, id);
    }
    @Override
    public void onInitialize() {
        ModItems.init();

        initPayload();
    }
    private void initPayload() {
        PayloadTypeRegistry.playC2S().register(HogyokuPayload.ID, HogyokuPayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(HogyokuPayload.ID, new HogyokuPayload.Receiver());

    }
}
