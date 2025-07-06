package com.koltino.trialanderror.particle;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class TabulaRasaParticles extends TextureSheetParticle {

    private final double targetX;
    private final double targetY;
    private final double targetZ;

    protected TabulaRasaParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteset, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.lifetime = 20 + this.random.nextInt(10);
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteset);

        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;

        double angleH = random.nextDouble() * 2 * Math.PI;
        double angleV = random.nextDouble() * Math.PI;
        double speed = 0.3 + random.nextDouble() * 0.3;
        this.xd = Math.cos(angleH) * Math.sin(angleV) * speed;
        this.yd = Math.cos(angleV) * speed;
        this.zd = Math.sin(angleH) * Math.sin(angleV) * speed;


    }

    @Override
    public void tick() {
        super.tick();

        double dx = targetX - this.x;
        double dy = targetY - this.y;
        double dz = targetZ - this.z;

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        if (distance > 0.01) {
            dx /= distance;
            dy /= distance;
            dz /= distance;

            double attractionStrength = 0.03;
            this.xd += dx * attractionStrength;
            this.yd += dy * attractionStrength;
            this.zd += dz * attractionStrength;
        }

        double v = 0.90;
        this.xd *= v;
        this.yd *= v;
        this.zd *= v;

        if (this.age > this.lifetime * 0.7f) {
            this.alpha = (this.lifetime - this.age) / (this.lifetime * 0.3f);
        }

    }

    @Override @MethodsReturnNonnullByDefault
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet spriteset;

        public Provider(SpriteSet spriteset){
            this.spriteset = spriteset;
        }

        @Override @ParametersAreNonnullByDefault
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double xv, double yv, double zv) {
            return new TabulaRasaParticles(clientLevel, x, y, z, this.spriteset, xv, yv, zv);
        }
    }
}
