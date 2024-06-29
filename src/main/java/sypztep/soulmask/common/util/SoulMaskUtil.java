package sypztep.soulmask.common.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import sypztep.soulmask.common.init.ModItems;

public class SoulMaskUtil {


    private static void unequip(PlayerEntity player) {
        player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
    }

    private static void equip(PlayerEntity player) {
        int rank = VizardComponentUtil.getHogyoku(player);
    }

//    @NotNull
//    private static ItemStack getItemStack(int baseValue) {
//        Item maskItem = switch (baseValue) {
//            case 2 -> ModItems.HOLLOW_MASK_TIER1;
//            case 3 -> ModItems.HOLLOW_MASK_TIER2;
//            case 4 -> ModItems.HOLLOW_MASK_TIER3;
//            case 5 -> ModItems.HOLLOW_MASK_TIER4;
//            case 6 -> ModItems.VASTO_MASK;
//            default -> ModItems.HALF_HOLLOW_MASK;
//        };
//        return new ItemStack(maskItem);
//    }
}
