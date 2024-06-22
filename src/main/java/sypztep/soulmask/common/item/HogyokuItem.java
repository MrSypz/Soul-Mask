package sypztep.soulmask.common.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import sypztep.soulmask.SoulMaskMod;
import sypztep.soulmask.common.payload.HogyokuPayload;
import sypztep.soulmask.common.util.VizardComponentUtil;

import java.util.List;

public class HogyokuItem extends Item {
    public HogyokuItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int rank = VizardComponentUtil.getHogyoku(user);
        boolean bl = rank < 6;
        if (world.isClient) {
            if (bl) {
                if (!user.isCreative())
                    stack.decrement(1);
                HogyokuPayload.send();
//                user.playSound(SoundEvents.BLOCK_END_PORTAL_SPAWN);
            }
            user.sendMessage(Text.literal(String.valueOf(rank)));
            return TypedActionResult.fail(stack);
        } else
        if (bl) {
            user.sendMessage(Text.translatable(SoulMaskMod.MODID + "hogyoku.consume").formatted(Formatting.GOLD), true);
            return TypedActionResult.success(stack);
        }
        user.sendMessage(Text.translatable(SoulMaskMod.MODID + ".hogyoku.limit_reached").formatted(Formatting.DARK_RED), true);
        return TypedActionResult.fail(stack);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        appendItemDescription(tooltip);
        super.appendTooltip(stack,context, tooltip, type);
    }

    private void appendItemDescription(List<Text> tooltip) {
        Item item = this;
        String translationKey = item.getTranslationKey();
        MutableText passive = (Text.translatable(translationKey + ".desc")).formatted(Formatting.GRAY);
        tooltip.add(passive);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
