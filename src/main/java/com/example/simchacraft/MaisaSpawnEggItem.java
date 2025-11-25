package com.example.simchacraft;

import net.minecraft.util.math.BlockPos;
import net.minecraft.text.Text;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.world.World;

// MaisaSpawnEggItem spawns our custom MaisaCreeper entity.
public class MaisaSpawnEggItem extends Item {

    public MaisaSpawnEggItem(Item.Settings settings) { 
        super(settings); 
    }

    /**
     * Handles the item usage when right-clicked on a block.
     */
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Only execute logic on the server side (not client)
        if (context.getWorld().isClient()) {
            return ActionResult.SUCCESS;
        }

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack itemStack = context.getStack();

        // Ensure we are on the server level for spawning entities
        if (world instanceof ServerWorld serverLevel) {
            // Create our custom Maisa Creeper
            MaisaCreeper maisa = new MaisaCreeper(SimchaCraftMod.MAISA_CREEPER, serverLevel);

            // Position the entity just above the clicked block
            maisa.setPos(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
            maisa.setCustomName(Text.literal("Maisa")); // Custom name
            maisa.setCustomNameVisible(true);

            // Add the entity to the world
            serverLevel.spawnEntity(maisa);

            // Consume one egg if not in creative mode
            if (!context.getPlayer().getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            if (context.getPlayer() != null) {
                context.getPlayer().sendMessage(Text.literal("A Maisa Creeper has been spawned!"), false);
            }

            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}