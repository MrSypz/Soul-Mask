package sypztep.soulmask.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.util.SoulMaskUtil;

public record MaskPayload() implements CustomPayload{
    public static final Id<MaskPayload> ID = CustomPayload.id("mask_equip_sync");
    public static final PacketCodec<PacketByteBuf, MaskPayload> CODEC = PacketCodec.unit(new MaskPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send() {
        ClientPlayNetworking.send(new MaskPayload());
    }
    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<MaskPayload> {
        @Override
        public void receive(MaskPayload payload, ServerPlayNetworking.Context context) {
            if (context.player() == null)
                return;
            SoulMaskUtil.handleEquipMask(context.player());
        }
    }
}
