package com.koltino.trialanderror.datagen;

import com.koltino.trialanderror.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ANDESITE_BULB.get());
        dropSelf(ModBlocks.ANDESITE_GRATE.get());
        dropSelf(ModBlocks.BRASS_GRATE.get());
        dropSelf(ModBlocks.ANDESITE_TRAPDOOR.get());


        /* add(ModBlocks.HYPOTHETICAL_ORE.get()
            block -> createOreDrop(The block, the item)
         */

    }

    @Override
    protected Iterable<Block> getKnownBlocks(){
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
