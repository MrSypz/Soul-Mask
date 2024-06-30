package sypztep.soulmask.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.component.VizardEnergyComponent;
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
            VizardComponent vizardComponent = ModEntityComponents.VIZARD.get(context.player());
            VizardEnergyComponent energyComponent = ModEntityComponents.VIZARD_ENERGY.get(context.player());

            vizardComponent.setHogyoku(vizardComponent.getHogyoku() + 1);
            energyComponent.setMaxEnergy(energyComponent.getMaxEnergy() + 400);
        }
    }
}
