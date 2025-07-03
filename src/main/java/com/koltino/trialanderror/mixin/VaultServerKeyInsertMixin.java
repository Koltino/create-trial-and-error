package com.koltino.trialanderror.mixin;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.entity.vault.VaultSharedData;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.UUID;

@Mixin(VaultBlockEntity.Server.class)
public abstract class VaultServerKeyInsertMixin {

    private static final ThreadLocal<UUID> keyUserThreadLocal = new ThreadLocal<>();

    private static UUID getKeyUser() {
        return keyUserThreadLocal.get();
    }

    private static void clearKeyUser() {
        keyUserThreadLocal.remove();
    }

    @Inject(method = "tryInsertKey", at = @At("HEAD"))
    private static void storeKeyUser(ServerLevel level, BlockPos pos, BlockState state, VaultConfig config, VaultServerData serverData, VaultSharedData sharedData, Player player, ItemStack stack, CallbackInfo ci) {
        keyUserThreadLocal.set(player.getUUID());
    }
}
