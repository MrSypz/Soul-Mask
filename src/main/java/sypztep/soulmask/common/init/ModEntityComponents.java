package sypztep.soulmask.common.init;

import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.component.VizardComponent;
import sypztep.soulmask.common.component.VizardEnergyComponent;

public class ModEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<VizardComponent> VIZARD = ComponentRegistry.getOrCreate(SoulMaskMod.id("vizard"), VizardComponent.class);
    public static final ComponentKey<VizardEnergyComponent> VIZARD_ENERGY = ComponentRegistry.getOrCreate(SoulMaskMod.id("vizard_energy"), VizardEnergyComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, VIZARD).respawnStrategy(RespawnCopyStrategy.CHARACTER).end(VizardComponent::new);
        registry.beginRegistration(PlayerEntity.class, VIZARD_ENERGY).respawnStrategy(RespawnCopyStrategy.CHARACTER).end(VizardEnergyComponent::new);
    }
}
