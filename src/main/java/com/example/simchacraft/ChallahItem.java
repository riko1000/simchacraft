package com.example.simchacraft;

import net.minecraft.text.Text;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A simple Challah item that shows a tooltip.
 */
public class ChallahItem extends Item {
    public ChallahItem(Item.Settings settings) {
        super(settings);
    }

    // Updated to Yarn 1.21.1 signature: appendTooltip(ItemStack, World, List<Component>, TooltipContext)
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        // This key "item.simchacraft.challah.tooltip" must be in your en_us.json
        tooltip.add(Text.translatable(this.getTranslationKey() + ".tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}