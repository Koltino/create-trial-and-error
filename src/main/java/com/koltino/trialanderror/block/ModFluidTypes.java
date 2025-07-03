package com.koltino.trialanderror.block;

import com.koltino.trialanderror.TrialAndError;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, TrialAndError.MOD_ID);

    public static final DeferredHolder<FluidType, FluidType> OMINOUS_BILE =
            FLUID_TYPES.register("ominous_bile",
                    () -> new OminousBileFluidType(FluidType.Properties.create()
                            .lightLevel(3)
                            .viscosity(2000)
                            .density(1500)
                            .canDrown(true)
                            .supportsBoating(true)
                    ));

    public static void register(IEventBus eventBus)
    {
        FLUID_TYPES.register(eventBus);
    }

}
