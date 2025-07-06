package com.koltino.trialanderror.item;

import com.koltino.trialanderror.mixin.VaultServerDataAccessor;
import com.koltino.trialanderror.particle.ModParticleTypes;
import com.koltino.trialanderror.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TabulaRasaItem extends Item {

    public boolean isOminous;

    public TabulaRasaItem(Properties properties, boolean isOminous) {
        super(properties);
        this.isOminous = isOminous;
    }


    @Override
    public InteractionResult useOn(UseOnContext context){
        Player player = context.getPlayer();
        Level level = context.getLevel();

        if(!level.isClientSide && player != null){
            BlockPos pos = context.getClickedPos();
            BlockEntity be = level.getBlockEntity(pos);
            if(be instanceof VaultBlockEntity vault){
                boolean isVaultOminous = be.getBlockState().getValue(VaultBlock.OMINOUS);
                VaultServerData serverData = vault.getServerData();
                assert serverData != null;
                Set<UUID> rewarded = ((VaultServerDataAccessor) serverData).getRewardedPlayers();
                if(isOminous || !isVaultOminous) {
                    if (rewarded.remove(player.getUUID())) {
                        level.playSound(null, context.getClickedPos(), ModSounds.TABULA_RASA_USE.get(), SoundSource.BLOCKS);

                        ((ServerLevel) level).sendParticles(ModParticleTypes.TABULA_RASA_PARTICLES.get(),
                                context.getClickedPos().getX() + 0.5, context.getClickedPos().getY() + 1,
                                context.getClickedPos().getZ() + 0.5, 15, 0, 0, 0, 2);


                        vault.setChanged();
                        if (!player.getAbilities().instabuild) {
                            context.getItemInHand().shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    } else {
                        level.playSound(null, context.getClickedPos(), SoundEvents.VAULT_REJECT_REWARDED_PLAYER, SoundSource.BLOCKS);

                    }
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
            if(isOminous)
            {
                tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_6").withStyle(ChatFormatting.GRAY));
            }else
            {
                tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_1").withStyle(ChatFormatting.GRAY));
            }
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_2").withStyle(ChatFormatting.GRAY));

            tooltip.add(Component.literal(""));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_3").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_4").withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.translatable("tooltip.trialanderror.tabula_rasa_5").withStyle(ChatFormatting.GRAY));


        }
    }
}
