package com.example.simchacraft;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.sound.SoundCategory;

import com.example.simchacraft.mixin.CreeperAccessor;

// MaisaCreeper is a custom Creeper that plays a custom sound when exploding.
public class MaisaCreeper extends CreeperEntity {

    private boolean playedSound = false;

    public MaisaCreeper(EntityType<? extends CreeperEntity> entityType, World level) {
        super(entityType, level);
    }

    /**
     * Overrides tick() to check the fuse state and play the custom sound 
     * just before the default Creeper logic triggers the explosion.
     */
    @Override
    public void tick() {
        super.tick();

        // Check if the Creeper is fused and the fuse is about to run out (distance <= 1)
        // getSwellDir() returns 1 when fusing, -1 when not.
        
        // CRITICAL FIX: Cast 'this' to the Mixin Accessor to reliably get the swell timer.
        int fuseTime = ((CreeperAccessor)this).getSwellTimer();

        if (this.getFuseSpeed() > 0 && fuseTime <= 1 && !playedSound) {
            
            if (!this.getWorld().isClient()) {
                // Play the custom sound (MAISA_POP_EVENT) defined in SimchaCraftMod
                this.getWorld().playSound(
                    null,
                    this.getBlockPos(),
                    SimchaCraftMod.MAISA_POP_EVENT,
                    SoundCategory.HOSTILE,
                    1.0F,
                    1.0F + this.getWorld().random.nextFloat() * 0.5F
                );
            }
            playedSound = true;
        }
        
        // If the Creeper's fuse is reset (like by being hit or running away), reset the playedSound flag
        if (this.getFuseSpeed() <= 0 && playedSound) {
            playedSound = false;
        }
    }
}