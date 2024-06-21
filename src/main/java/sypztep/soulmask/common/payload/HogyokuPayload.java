package sypztep.soulmask.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.init.ModEntityComponents;

public record HogyokuPayload() implements CustomPayload{
    public static final Id<HogyokuPayload> ID = CustomPayload.id("hogyoku_sync");
    public static final PacketCodec<PacketByteBuf, HogyokuPayload> CODEC = PacketCodec.unit(new HogyokuPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
    public static void send() {
        ClientPlayNetworking.send(new HogyokuPayload());
    }
    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<HogyokuPayload> {
        @Override
        public void receive(HogyokuPayload payload, ServerPlayNetworking.Context context) {
            VizardComponent component = ModEntityComponents.VIZARD.get(context.player());
            component.setHogyoku(component.getHogyoku() + 1);
        }
    }
}
