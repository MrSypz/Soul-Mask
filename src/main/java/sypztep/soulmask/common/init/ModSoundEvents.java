package sypztep.soulmask.common.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static RegistryEntry.Reference<SoundEvent> ENTITY_GENERIC_CHARGE2;
    public static void init() {
        ENTITY_GENERIC_CHARGE2 = registerReference("entity.generic.charge2");
    }
    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(Identifier.ofVanilla(id));
    }
    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }
}
