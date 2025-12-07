package com.example.simchacraft;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

/**
 * A small creature that occasionally "vapes" for flavor: spawns smoke particles and gains a brief buff.
 */
public class NevoNahmiasEntity extends PathAwareEntity {
    private int smokeCooldown = 0;
    private int inhaleTimer = 0;

    protected NevoNahmiasEntity(EntityType<? extends PathAwareEntity> type, World world) {
        super(type, world);
        // stagger inhale timers so not all Nevo inhale at the same tick
        this.inhaleTimer = this.random.nextInt(100);
    }

    public static DefaultAttributeContainer.Builder createNevoAttributes() {
        return PathAwareEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        // Use a custom erratic wander goal so Nevo walks in a silly, unpredictable way
        this.goalSelector.add(2, new ErraticWanderGoal(this, 1.0));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    /**
     * A small custom goal that causes the mob to wander in short, jerky bursts
     * and pick random nearby targets, producing a "dumb guy" walking vibe.
     */
    private static class ErraticWanderGoal extends Goal {
        private final NevoNahmiasEntity mob;
        private final double speed;

        ErraticWanderGoal(NevoNahmiasEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
        }

        @Override
        public boolean canStart() {
            // Sometimes start wandering when navigation is idle; randomness makes it unpredictable
            if (!this.mob.getNavigation().isIdle()) return false;
            return this.mob.random.nextInt(30) == 0;
        }

        @Override
        public boolean shouldContinue() {
            return !this.mob.getNavigation().isIdle();
        }

        @Override
        public void start() {
            // Pick a random nearby position within a roughly 6-8 block radius and start moving
            double dx = this.mob.getX() + (this.mob.random.nextDouble() - 0.5) * 12.0;
            double dz = this.mob.getZ() + (this.mob.random.nextDouble() - 0.5) * 12.0;
            double dy = this.mob.getY();
            this.mob.getNavigation().startMovingTo(dx, dy, dz, this.speed);
        }

        @Override
        public void tick() {
            // Occasionally interrupt the current path and do a short burst in another random direction
            if (this.mob.random.nextInt(200) == 0 && this.mob.getNavigation().isIdle()) {
                double dx = this.mob.getX() + (this.mob.random.nextDouble() - 0.5) * 4.0;
                double dz = this.mob.getZ() + (this.mob.random.nextDouble() - 0.5) * 4.0;
                double dy = this.mob.getY();
                this.mob.getNavigation().startMovingTo(dx, dy, dz, this.speed * 1.2);
            }

            // Small chance to do a short, slower shuffle (pausing-looking around effect)
            if (this.mob.random.nextInt(300) == 0) {
                // Stop movement briefly by clearing the path
                this.mob.getNavigation().stop();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient()) {
            if (smokeCooldown > 0) smokeCooldown--;
            if (smokeCooldown <= 0 && this.random.nextInt(600) == 0) {
                for (int i = 0; i < 8; i++) {
                    double dx = (this.random.nextDouble() - 0.5) * 0.1;
                    double dy = 0.02 + this.random.nextDouble() * 0.05;
                    double dz = (this.random.nextDouble() - 0.5) * 0.1;
                    this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 1.6, this.getZ(), dx, dy, dz);
                }
                this.playSound(SoundEvents.ENTITY_PLAYER_BURP, 0.6F, 1.0F);
                smokeCooldown = 200;
            }
        } else {
            // Ensure Nevo is holding the vape on the server so the equipped item syncs to clients
            if (!this.getStackInHand(Hand.MAIN_HAND).isOf(SimchaCraftMod.VAPE)) {
                this.setStackInHand(Hand.MAIN_HAND, new ItemStack(SimchaCraftMod.VAPE));
            }

            // Inhale every 5 seconds (100 ticks)
            if (this.inhaleTimer > 0) this.inhaleTimer--;
            if (this.inhaleTimer <= 0) {
                // Only inhale if holding a vape
                if (this.getStackInHand(Hand.MAIN_HAND).isOf(SimchaCraftMod.VAPE)) {
                    // Spawn particles for all players
                    if (this.getWorld() instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 1.6, this.getZ(), 12, 0.1, 0.05, 0.1, 0.02);
                        this.getWorld().playSound(null, this.getBlockPos(), SimchaCraftMod.VAPE_INHALE_EVENT, net.minecraft.sound.SoundCategory.NEUTRAL, 0.7F, 1.0F);
                    }

                    // Apply nausea and poison as harmful effects from vaping
                    this.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.NAUSEA, 20 * 6, 0));
                    this.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.POISON, 20 * 4, 0));
                }

                this.inhaleTimer = 100;
            }
        }
    }

    @Override
    public Text getName() {
        return Text.literal("Nevo Nahmias");
    }
}
