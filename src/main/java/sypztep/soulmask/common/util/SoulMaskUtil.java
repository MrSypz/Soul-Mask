package sypztep.soulmask.common.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import sypztep.soulmask.common.init.ModItems;
import sypztep.soulmask.common.item.MaskItem;

public class SoulMaskUtil {

    public static boolean hasEquippedMask(LivingEntity living) {
        ItemStack stack = living.getEquippedStack(EquipmentSlot.HEAD);
        return stack.getItem() instanceof MaskItem;
    }

    public static void handleEquipMask(PlayerEntity player) {
        boolean hasMask = hasEquippedMask(player);
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        int emptySlot = player.getInventory().getEmptySlot();
        if (headSlot.isEmpty()) {
            equip(player);
        } else if (hasMask) {
            unequip(player);
        } else {
            if (emptySlot >= 0) {
                player.getInventory().setStack(emptySlot, headSlot);
            } else {
                player.dropItem(headSlot, true); // Drop the item
            }
            equip(player);
        }
    }

    private static void unequip(PlayerEntity player) {
        player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
    }

    private static void equip(PlayerEntity player) {
        int rank = VizardComponentUtil.getHogyoku(player);
        ItemStack hollowMask = getItemStack(rank);
        player.equipStack(EquipmentSlot.HEAD, hollowMask);
    }

    @NotNull
    private static ItemStack getItemStack(int baseValue) {
        Item maskItem = switch (baseValue) {
            case 2 -> ModItems.HOLLOW_MASK_TIER1;
            case 3 -> ModItems.HOLLOW_MASK_TIER2;
            case 4 -> ModItems.HOLLOW_MASK_TIER3;
            case 5 -> ModItems.HOLLOW_MASK_TIER4;
            case 6 -> ModItems.VASTO_MASK;
            default -> ModItems.HALF_HOLLOW_MASK;
        };
        return new ItemStack(maskItem);
    }
}
