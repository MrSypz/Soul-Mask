package sypztep.soulmask.common.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import sypztep.soulmask.common.init.ModEntityComponents;

public class VizardComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity obj;
    private int hogyoku = 0;
    private boolean equipMask = false;

    public VizardComponent(PlayerEntity obj) {
        this.obj = obj;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.hogyoku = tag.getInt("Hogyoku");
        this.equipMask = tag.getBoolean("EquipMask");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Hogyoku", this.hogyoku);
        tag.putBoolean("EquipMask", this.equipMask);
    }

    public static VizardComponent getComponent(PlayerEntity player) {
        return ModEntityComponents.VIZARD.get(player);
    }

    public int getHogyoku() {
        return hogyoku;
    }

    public void setHogyoku(int hogyoku) {
        this.hogyoku = hogyoku;
        sync();
    }

    public boolean isEquipMask() {
        return equipMask;
    }

    public void setEquipMask(boolean equipMask) {
        this.equipMask = equipMask;
        sync();
    }

    public void sync() {
        ModEntityComponents.VIZARD.sync(this.obj);
    }

    @Override
    public void clientTick() {
        tick();
        System.out.println(equipMask);
//        hasEquipMask = SoulMaskUtil.hasEquippedMask(obj);
    }

    @Override
    public void tick() {

    }
}
