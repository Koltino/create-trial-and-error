package com.koltino.trialanderror.data;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TrialAndError.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ANDESITE_GRATE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BRASS_GRATE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ANDESITE_BULB.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ANDESITE_TRAPDOOR.get());




        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ANDESITE_GRATE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BRASS_GRATE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ANDESITE_BULB.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ANDESITE_TRAPDOOR.get());




    }
}