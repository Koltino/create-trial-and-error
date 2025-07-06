package com.koltino.trialanderror.item;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TrialAndError.MOD_ID);

    public static final Supplier<CreativeModeTab> TRIALANDERROR_TAB = CREATIVE_MODE_TABS.register("trialanderror_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.OMINOUS_BILE_BUCKET.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ANDESITE_GRATE);
                        output.accept(ModBlocks.BRASS_GRATE);
                        output.accept(ModBlocks.ANDESITE_BULB);
                        output.accept(ModBlocks.ANDESITE_TRAPDOOR);
                        output.accept(ModItems.TABULA_RASA);
                        output.accept(ModItems.COMMON_TABULA_RASA);
                        output.accept(ModItems.TRIAL_KEY_CAST);
                        output.accept(ModItems.OMINOUS_KEY_CAST);
                        output.accept(ModItems.TRIAL_KEY_CAST_FILLED);
                        output.accept(ModItems.OMINOUS_KEY_CAST_FILLED);
                        output.accept(ModItems.OMINOUS_BILE_BUCKET);
                    })
                    .title(Component.translatable("creativetab.trialanderror.tab"))
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
