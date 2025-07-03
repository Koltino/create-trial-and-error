package com.koltino.trialanderror.block;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, TrialAndError.MOD_ID);


    public static final DeferredHolder<Fluid, Fluid> OMINOUS_BILE =
            FLUIDS.register("ominous_bile", () -> new OminousBileFluid.Source(getOminousBileProperties()));

    public static final DeferredHolder<Fluid, Fluid> FLOWING_OMINOUS_BILE =
            FLUIDS.register("flowing_ominous_bile", () -> new OminousBileFluid.Flowing(getOminousBileProperties()));

    private static BaseFlowingFluid.Properties getOminousBileProperties() {
        return new BaseFlowingFluid.Properties(
                ModFluidTypes.OMINOUS_BILE,
                OMINOUS_BILE,
                FLOWING_OMINOUS_BILE
        ).bucket(ModItems.OMINOUS_BILE_BUCKET)
                .block(ModBlocks.OMINOUS_BILE_BLOCK)
                .tickRate(10)
                .levelDecreasePerBlock(2)
                .slopeFindDistance(2)
                .explosionResistance(0F);
    }


    public static void register(IEventBus eventBus)
    {
        FLUIDS.register(eventBus);
    }



}
