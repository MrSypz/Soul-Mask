package sypztep.soulmask.common.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.payload.EnergyPayload;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class VizardEnergyComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity obj;
    private int energy = 100;

    public VizardEnergyComponent(PlayerEntity player) {
        this.obj = player;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.energy = tag.getInt("energy");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("energy", this.energy);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        this.energy = buf.readVarInt();
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeVarInt(this.energy);
    }
    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.obj;
    }

    public static VizardEnergyComponent getComponent(PlayerEntity player) {
        return ModEntityComponents.VIZARD_ENERGY.get(player);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        sync();
    }
    @Override
    public void serverTick() {
//        drainNRegenEnergy();
//        System.out.println("Server: " + VizardEnergyComponent.getComponent(obj).getEnergy());
        tick();
    }

    @Override
    public void clientTick() {
        if (VizardComponentUtil.isHasEquipMask(obj))
            EnergyPayload.send((byte) 0);
         else
             EnergyPayload.send((byte) 1);
        tick();
    }

    @Override
    public void tick() {
    }
    public void sync() {
        ModEntityComponents.VIZARD_ENERGY.sync(this.obj);
    }
}
