package com.koltino.trialanderror.block;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TrialAndError.MOD_ID);

    public static final DeferredBlock<Block> BRASS_GRATE = registerBlock("brass_grate",
            () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE)));

    public static final DeferredBlock<Block> ANDESITE_GRATE = registerBlock("andesite_grate",
            () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE)));

    public static final DeferredBlock<Block> ANDESITE_BULB = registerBlock("andesite_bulb",
            () -> new CopperBulbBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BULB)));

    public static final DeferredBlock<TrapDoorBlock> ANDESITE_TRAPDOOR = registerBlock("andesite_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.of().strength(2.5f).requiresCorrectToolForDrops().noOcclusion()));

    public static final DeferredBlock<LiquidBlock> OMINOUS_BILE_BLOCK = registerBlock("ominous_bile",
            () -> new LiquidBlock((FlowingFluid) ModFluids.OMINOUS_BILE.get(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_PURPLE)
                            .noCollission()
                            .strength(100.0F)
                            .noLootTable()
                            .replaceable()
            )
    );

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> deferredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, deferredBlock);
        return deferredBlock;
    }

    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }



    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
