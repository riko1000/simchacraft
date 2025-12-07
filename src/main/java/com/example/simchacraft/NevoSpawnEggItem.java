package com.example.simchacraft;

import net.minecraft.util.math.BlockPos;
import net.minecraft.text.Text;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.world.World;

// NevoSpawnEggItem spawns our custom Nevo Nahmias entity.
public class NevoSpawnEggItem extends Item {

    public NevoSpawnEggItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            return ActionResult.SUCCESS;
        }

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack itemStack = context.getStack();

        if (world instanceof ServerWorld serverLevel) {
            NevoNahmiasEntity nevo = new NevoNahmiasEntity(SimchaCraftMod.NEVO_NAHMIAS, serverLevel);

            // Find a safe spawn Y so the entity doesn't clip into blocks.
            BlockPos spawnPos = pos.up();
            // If the clicked space is not free, try searching up to 5 blocks above
            int attempts = 0;
            while (attempts < 5 && !serverLevel.isAir(spawnPos) && !serverLevel.isAir(spawnPos.up())) {
                spawnPos = spawnPos.up();
                attempts++;
            }

            nevo.setPos(spawnPos.getX() + 0.5, spawnPos.getY() + 0.0, spawnPos.getZ() + 0.5);
            nevo.setCustomName(Text.literal("Nevo Nahmias"));
            nevo.setCustomNameVisible(true);

            serverLevel.spawnEntity(nevo);

            if (context.getPlayer() != null && !context.getPlayer().getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            if (context.getPlayer() != null) {
                context.getPlayer().sendMessage(Text.literal("Nevo Nahmias spawned."), false);
            }

            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}
