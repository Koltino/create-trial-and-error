package com.koltino.trialanderror.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FogType;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class OminousBileFluidType extends FluidType {
    private final ResourceLocation still = ResourceLocation.fromNamespaceAndPath("trialanderror", "block/ominous_bile_still");;
    private final ResourceLocation flowing = ResourceLocation.fromNamespaceAndPath("trialanderror", "block/ominous_bile_flow");;

    public OminousBileFluidType(Properties properties) {
        super(properties);
    }



    @Override
    public void initializeClient(@NotNull Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return still;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowing;
            }
        });
    }
}