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
    private int energy = 100;

    public VizardEnergyComponent(PlayerEntity player) {
        this.obj = player;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.energy = tag.getInt("Energy");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Energy", this.energy);
    }

    public static VizardEnergyComponent getComponent(PlayerEntity player) {
        return ModEntityComponents.VIZARD_ENERGY.get(player);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
        ModEntityComponents.VIZARD_ENERGY.sync(this.obj);
    }

    /**
     * Energy it gonna only handle on client side why?
     * cuz when handle with server it fuck up
     */
    @Override
    public void clientTick() {
        tick();
        if (VizardComponentUtil.isEquipMask(this.obj) && this.energy > 0) {
            EnergyPayload.send(this.obj.getId(), 0);
        } else if (this.energy < 100) {
            EnergyPayload.send(this.obj.getId(), 1);
        }
    }

    @Override
    public void tick() {

    }
}
