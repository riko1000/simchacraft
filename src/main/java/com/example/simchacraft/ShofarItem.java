package com.example.simchacraft;

import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.List;

/**
 * A playful Shofar item that plays a sound, gives a buff, and shows a tooltip.
 */
public class ShofarItem extends Item {
    public ShofarItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            // Play our custom shofar blow sound
            world.playSound(null, user.getBlockPos(), SimchaCraftMod.SHOFAR_BLOW_EVENT, SoundCategory.PLAYERS, 1.0f, 1.0f);

            // Give the player a small strength boost for 10 seconds (20 ticks/sec * 10 sec = 200 ticks)
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 20 * 10, 0));
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType tooltipType) {
        // This key "item.simchacraft.shofar.tooltip" must be in your en_us.json
        tooltip.add(Text.translatable(this.getTranslationKey() + ".tooltip"));
        super.appendTooltip(stack, context, tooltip, tooltipType);
    }
}