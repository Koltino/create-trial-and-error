package com.koltino.trialanderror.mixin;

import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "net.minecraft.world.level.block.entity.vault.VaultBlockEntity$Server")
public class VaultBlockEntityServerMixin {
    @Overwrite
    private static boolean canEjectReward(VaultConfig config, VaultState state) {
        return config.lootTable() != BuiltInLootTables.EMPTY && !config.keyItem().isEmpty();
    }
}