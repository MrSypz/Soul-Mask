package sypztep.soulmask.common.component;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public class PowerComponent implements AutoSyncedComponent {
    final int MAX_DEFAULT = 100;
    int energy = MAX_DEFAULT;
    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.energy = tag.getInt("energy");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("energy",this.energy);
    }
}
