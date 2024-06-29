package sypztep.soulmask.common.init;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.item.HogyokuItem;

import java.util.Set;

public class ModItems {
    public static Item HOGYOKU;
//
    public static void init() {

        HOGYOKU = registerItem("hogyoku", new HogyokuItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));
        itemGroup();
    }
    private static <T extends Item> T registerItem(String name, T item) {
        Registry.register(Registries.ITEM, SoulMaskMod.id(name), item);
        return item;
    }
    private static void itemGroup() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add(ModItems.HOGYOKU);
        });
    }
}
