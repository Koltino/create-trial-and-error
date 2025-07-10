package com.koltino.trialanderror.data;

import com.koltino.trialanderror.TrialAndError;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = TrialAndError.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModConditions {
    @SubscribeEvent
    public static void registerConditionSerializers(RegisterEvent event) {
        if (!event.getRegistryKey().equals(NeoForgeRegistries.CONDITION_SERIALIZERS.key())) {
            return; // stop blowing up my line
        }
        System.out.println("[trialanderror] Registering custom conditions...");
        event.register(NeoForgeRegistries.CONDITION_SERIALIZERS.key(), helper -> {
            helper.register(
                    ResourceLocation.fromNamespaceAndPath("trialanderror", "overwrite_trial_chamber"),
                    (MapCodec<? extends ICondition>) PaletteSwapCondition.MAP_CODEC
            );
        });
    }
}
