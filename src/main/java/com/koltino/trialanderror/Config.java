package com.koltino.trialanderror;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
//@EventBusSubscriber(modid = TrialAndError.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();


    private static final ModConfigSpec.BooleanValue PALETTE_SWAP = BUILDER
            .comment("Use palette swapped trial chambers with Create blocks")
            .define("paletteSwapTrialChambers", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean paletteSwapTrialChambers;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        paletteSwapTrialChambers = PALETTE_SWAP.get();

    }
}
