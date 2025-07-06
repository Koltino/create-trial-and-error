package com.koltino.trialanderror.mixin;

import com.koltino.trialanderror.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.VaultBlock;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VaultBlock.class)
public class VaultBlockStateSpoofer {

    @Redirect(
            method = "useItemOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;"
            )
    )
    private Comparable<?> allowInactiveState(BlockState state, Property<?> property) {
        if (property == VaultBlock.STATE) {
            VaultState current = (VaultState) state.getValue(VaultBlock.STATE);
            if (current == VaultState.INACTIVE) {
                return VaultState.ACTIVE;
            }
        }
        return state.getValue(property);
    }

    @Inject(method = "useItemOn", at = @At("HEAD"), cancellable = true)
    private void bypassForTabulaRasa(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit,
            CallbackInfoReturnable<ItemInteractionResult> cir
    ) {
        if (stack.is(ModItems.TABULA_RASA.get()) || stack.is(ModItems.COMMON_TABULA_RASA.get())) {
            //skips the spoofed active state behavior and fallback to default interaction
            cir.setReturnValue(ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION);
        }
    }

}
