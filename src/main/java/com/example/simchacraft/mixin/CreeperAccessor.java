package com.example.simchacraft.mixin;

import net.minecraft.entity.mob.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Mixin Accessor for the Creeper class.
 * This interface allows us to reliably read the private 'swell' field (the fuse timer),
 * which is often renamed by Minecraft mappings and causes 'cannot find symbol' errors.
 */
@Mixin(CreeperEntity.class)
public interface CreeperAccessor {

    // Accessor annotation targets the private 'currentFuseTime' field (fuse timer) in 1.21.1
    @Accessor("currentFuseTime")
    int getSwellTimer();
}