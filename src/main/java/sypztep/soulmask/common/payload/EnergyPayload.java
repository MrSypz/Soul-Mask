package sypztep.soulmask.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.component.VizardEnergyComponent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.util.SoulMaskUtil;

public record EnergyPayload(byte select) implements CustomPayload {
    public static final Id<EnergyPayload> ID = CustomPayload.id("mask_energy_sync");
    public static final PacketCodec<PacketByteBuf, EnergyPayload> CODEC = PacketCodec.tuple(PacketCodecs.BYTE, EnergyPayload::select, EnergyPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(byte select) {
        ClientPlayNetworking.send(new EnergyPayload(select));
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<EnergyPayload> {
        @Override
        public void receive(EnergyPayload payload, ServerPlayNetworking.Context context) {
            VizardEnergyComponent energyComponent = ModEntityComponents.VIZARD_ENERGY.get(context.player());
            switch (payload.select()) {
                case 0:
                    if (energyComponent.getEnergy() > 0)
                        energyComponent.setEnergy(energyComponent.getEnergy() - 1);

                    break;
                case 1:
                    if (energyComponent.getEnergy() < 100)
                        energyComponent.setEnergy(energyComponent.getEnergy() + 1);
                    break;
            }
        }
    }
}
