package sypztep.soulmask.common.payload;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import sypztep.soulmask.common.component.VizardEnergyComponent;
import sypztep.soulmask.common.init.ModEntityComponents;

public record EnergyPayload(int entityId, int select) implements CustomPayload {
    public static final Id<EnergyPayload> ID = CustomPayload.id("energy_sync");
    public static final PacketCodec<PacketByteBuf, EnergyPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT,
            EnergyPayload::entityId,
            PacketCodecs.VAR_INT,
            EnergyPayload::select,
            EnergyPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void send(int entityId, int select) {
        ClientPlayNetworking.send(new EnergyPayload(entityId, select));
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<EnergyPayload> {
        @Override
        public void receive(EnergyPayload payload, ServerPlayNetworking.Context context) {
            if (context.player() == null) return;
            VizardEnergyComponent energyComponent = ModEntityComponents.VIZARD_ENERGY.getNullable(context.player().getWorld().getEntityById(payload.entityId));
            if (energyComponent == null) return;

            if (payload.select() == 0) {
                energyComponent.setEnergy(energyComponent.getEnergy() - 1);
            }
            if (payload.select() == 1) {
                energyComponent.setEnergy(energyComponent.getEnergy() + 1);
            }
        }
    }
    public enum handleEnergy {
        DRAIN((byte) 0),
        REGEN((byte) 1);
        private final byte flag;
        handleEnergy(byte flag){
            this.flag = flag;
        }

        public byte task() {
            return flag;
        }
    }
}
