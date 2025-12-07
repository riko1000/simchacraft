package com.example.simchacraft;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
// using SimchaCraftMod from same package; no import required
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
 

/**
 * Simple "vape" item: emits smoke particles, plays a sound, and grants a short benign effect for flavor.
 * Has a 3-second cooldown between uses.
 */
public class VapeItem extends Item {
    private static final int COOLDOWN_TICKS = 3 * 20; // 3 seconds = 60 ticks (20 ticks/second)

    public VapeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            // Apply Nausea and Poison for about 4-5 seconds (use 5s here)
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 5, 0)); // ~5 seconds of Nausea
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 20 * 5, 0)); // ~5 seconds of Poison

            // Play our custom inhale sound on the server so nearby players hear it
            world.playSound(null, user.getBlockPos(), SimchaCraftMod.VAPE_INHALE_EVENT, net.minecraft.sound.SoundCategory.PLAYERS, 1.0F, 1.0F);
            
            // Set the item cooldown for 4 seconds
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
        } else {
            // Client-side visual + sound
            for (int i = 0; i < 12; i++) {
                double dx = (user.getRandom().nextDouble() - 0.5) * 0.2;
                double dy = 0.1 + user.getRandom().nextDouble() * 0.1;
                double dz = (user.getRandom().nextDouble() - 0.5) * 0.2;
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, user.getX(), user.getY() + 1.4, user.getZ(), dx, dy, dz);
            }
            // Play client-side inhale sound for immediate feedback
            user.playSound(SimchaCraftMod.VAPE_INHALE_EVENT, 1.0F, 1.0F);
        }

        return TypedActionResult.success(stack);
    }
}
