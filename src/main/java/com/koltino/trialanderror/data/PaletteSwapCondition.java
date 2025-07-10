package com.koltino.trialanderror.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.koltino.trialanderror.Config;
import net.neoforged.neoforge.common.conditions.ICondition;

public record PaletteSwapCondition() implements ICondition {
    public static final MapCodec<PaletteSwapCondition> MAP_CODEC =
            MapCodec.unit(new PaletteSwapCondition());

    public static final Codec<PaletteSwapCondition> CODEC = MAP_CODEC.codec();

    @Override
    public boolean test(IContext context) {
        return Config.paletteSwapTrialChambers;
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return MAP_CODEC;
    }
}
