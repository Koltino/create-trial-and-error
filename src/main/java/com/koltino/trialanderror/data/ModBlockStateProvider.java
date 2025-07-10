package com.koltino.trialanderror.data;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TrialAndError.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        trapdoorBlockWithRenderType(ModBlocks.ANDESITE_TRAPDOOR.get(), modLoc("block/andesite_trapdoor"), true, "cutout");
        blockWithItem(ModBlocks.ANDESITE_TRAPDOOR);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
