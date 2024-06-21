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

    private static ItemStack getHeadSlot(LivingEntity living) {
        return living.getEquippedStack(EquipmentSlot.HEAD);
    }

    public static boolean hasEquippedMask(LivingEntity living) {
        ItemStack stack = living.getEquippedStack(EquipmentSlot.HEAD);
        return stack.getItem() instanceof MaskItem;
    }

    private static boolean hasAnyMask(PlayerEntity player) {
        for (ItemStack stack : player.getInventory().armor) {
            if (ModItems.ALL_MASK.contains(stack.getItem())) {
                return true;
            }
        }
        return false;
    }

    public static void handleEquipMask(PlayerEntity player) {
        boolean hasMask = hasAnyMask(player);
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);

        if (headSlot.isEmpty()) {
            equipMask(player);
        } else if (!hasMask) {
            int emptySlot = player.getInventory().getEmptySlot();
            if (emptySlot >= 0) {
                player.getInventory().setStack(emptySlot, headSlot);
            } else {
                player.dropItem(headSlot, true); // Drop the item
            }
            equipMask(player);
        }
    }

    public static void unequipMask(PlayerEntity player) {
        player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
    }

    public static void equipMask(PlayerEntity player) {
        int rank = VizardUtil.getHogyoku(player);
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
            default -> ModItems.HALF_HOLLOW_MASK;
        };
        return new ItemStack(maskItem);
    }
}
