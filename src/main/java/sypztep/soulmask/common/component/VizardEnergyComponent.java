package sypztep.soulmask.common.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.payload.EnergyPayload;
import sypztep.soulmask.common.util.VizardComponentUtil;

public class VizardEnergyComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity obj;
    private int maxEnergy;
    private int energy;

    public VizardEnergyComponent(PlayerEntity player) {
        this.obj = player;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.energy = tag.getInt("Energy");
        this.maxEnergy = tag.getInt("MaxEnergy");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Energy", this.energy);
        tag.putInt("MaxEnergy", this.maxEnergy);
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
    public void sync() {
        ModEntityComponents.VIZARD_ENERGY.sync(this.obj);
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
        sync();
    }

    /**
     * Energy it gonna only handle on client side why?
     * cuz when handle with server it fuck up
     */
    @Override
    public void clientTick() {
        tick();
        if (VizardComponentUtil.isEquipMask(this.obj) && this.energy > 0) {
            EnergyPayload.send(this.obj.getId(), EnergyPayload.handleEnergy.DRAIN.task());
        } else if (this.energy < maxEnergy) {
            EnergyPayload.send(this.obj.getId(), EnergyPayload.handleEnergy.REGEN.task());
        }
    }

    @Override
    public void tick() {

    }
}
