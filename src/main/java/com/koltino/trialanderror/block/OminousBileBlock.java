package com.koltino.trialanderror.block;

import com.koltino.trialanderror.TrialAndError;
import com.koltino.trialanderror.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class OminousBileBlock extends LiquidBlock {

    public OminousBileBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            Vec3 eyePos = livingEntity.getEyePosition();
            if (level.getBlockState(BlockPos.containing(eyePos)).getBlock() == this) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0, false, false, true));
            }
            MobEffectInstance wither = livingEntity.getEffect(MobEffects.WITHER);
            if (wither == null || wither.getDuration() <= 10) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0, false, true, true));
            }

        }


    }

}