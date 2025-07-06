package com.koltino.trialanderror.item;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.block.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TrialAndError.MOD_ID);

    public static final DeferredItem<Item> TRIAL_KEY_CAST = ITEMS.register("trial_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TRIAL_KEY_CAST_FILLED = ITEMS.register("filled_trial_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> OMINOUS_KEY_CAST = ITEMS.register("ominous_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> OMINOUS_KEY_CAST_FILLED = ITEMS.register("filled_ominous_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INCOMPLETE_TRIAL_KEY_CAST = ITEMS.register("incomplete_filled_trial_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INCOMPLETE_OMINOUS_KEY_CAST_FILLED = ITEMS.register("incomplete_filled_ominous_key_cast",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> TABULA_RASA = ITEMS.register("tabula_rasa",
            () -> new TabulaRasaItem(new Item.Properties(), true));

    public static final DeferredItem<Item> COMMON_TABULA_RASA = ITEMS.register("common_tabula_rasa",
            () -> new TabulaRasaItem(new Item.Properties(), false));


    public static final DeferredItem<Item> OMINOUS_BILE_BUCKET =
            ITEMS.register("ominous_bile_bucket",
            () -> new BucketItem(ModFluids.OMINOUS_BILE.get(), new Item.Properties()
                    .craftRemainder(Items.BUCKET)
                    .stacksTo(1)
                    .durability(0)
                    )
            );




    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
