package sypztep.soulmask.common.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
import sypztep.soulmask.common.init.ModEntityComponents;
import sypztep.soulmask.common.util.SoulMaskUtil;

public class VizardComponent implements AutoSyncedComponent, CommonTickingComponent {
    private final PlayerEntity obj;
    private int hogyoku = 0;
    private boolean hasEquipMask = false;

    public VizardComponent(PlayerEntity obj) {
        this.obj = obj;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.hogyoku = tag.getInt("hogyoku");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("hogyoku",this.hogyoku);
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

    public boolean isHasEquipMask() {
        return hasEquipMask;
    }
    public void sync() {
        ModEntityComponents.VIZARD.sync(this.obj);
    }

    @Override
    public void clientTick() {
        tick();
        hasEquipMask = SoulMaskUtil.hasEquippedMask(obj);
    }

    @Override
    public void tick() {

    }
}
