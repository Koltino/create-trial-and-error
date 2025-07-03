package com.koltino.trialanderror.item;

import com.koltino.trialanderror.mixin.VaultServerDataAccessor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TabulaRasaItem extends Item {

    public TabulaRasaItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult useOn(UseOnContext context){
        Player player = context.getPlayer();
        Level level = context.getLevel();
        if(!level.isClientSide && player != null){
            BlockPos pos = context.getClickedPos();
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof VaultBlockEntity vault){
                VaultServerData serverData = vault.getServerData();
                assert serverData != null;
                Set<UUID> rewarded = ((VaultServerDataAccessor) serverData).getRewardedPlayers();

                if (rewarded.remove(player.getUUID())) {
                    player.displayClientMessage(Component.literal("You may reattempt the vault."), true);
                    vault.setChanged();
                    if (!player.getAbilities().instabuild) {
                        context.getItemInHand().shrink(1);
                    }
                    return InteractionResult.SUCCESS;
                } else {
                    player.displayClientMessage(Component.literal("The vault is ready."), true);
                }


            }
        }
        return InteractionResult.PASS;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        boolean shiftDown = Screen.hasShiftDown();

        Component hold = Component.literal("Hold [").withStyle(ChatFormatting.DARK_GRAY);
        Component shift = Component.literal("Shift").withStyle(shiftDown ? ChatFormatting.WHITE : ChatFormatting.GRAY);
        Component forSummary = Component.literal("] for Summary").withStyle(ChatFormatting.DARK_GRAY);

        Component combined = Component.empty()
                .append(hold)
                .append(shift)
                .append(forSummary);

        tooltip.add(combined);

        if (shiftDown) {
            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_1").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_2").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_3").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_4").withStyle(ChatFormatting.GRAY));


        }
    }
}
