package com.koltino.trialanderror.mixin;

import com.koltino.trialanderror.mixin.VaultServerDataAccessor;
import com.koltino.trialanderror.mixin.VaultSharedDataAccessor;
import com.koltino.trialanderror.tools.VaultKeyHelper;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.vault.VaultConfig;
import net.minecraft.world.level.block.entity.vault.VaultServerData;
import net.minecraft.world.level.block.entity.vault.VaultSharedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;
import java.util.UUID;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;

@Mixin(VaultSharedData.class)
public class VaultSharedDataMixin {

    @Inject(method = "updateConnectedPlayersWithinRange", at = @At("HEAD"), cancellable = true)
    private void useOnlyKeyUser(ServerLevel level, BlockPos pos, VaultServerData serverData, VaultConfig config, double deactivationRange, CallbackInfo ci) {
        UUID keyUser = VaultKeyHelper.getKeyUser();
        if (keyUser != null) {
            Set<UUID> players = new ObjectLinkedOpenHashSet<>();
            players.add(keyUser);

            VaultSharedDataAccessor accessor = (VaultSharedDataAccessor) (Object) this;
            accessor.getConnectedPlayers().clear();
            accessor.getConnectedPlayers().addAll(players);
            accessor.setIsDirty(true);

            VaultKeyHelper.clearKeyUser();
            ci.cancel();
        }
    }
}
