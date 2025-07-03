package com.koltino.trialanderror.datagen;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TrialAndError.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}