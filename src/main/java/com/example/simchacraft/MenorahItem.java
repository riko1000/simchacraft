package com.example.simchacraft;

import net.minecraft.text.Text;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
// Item.TooltipContext exists in Yarn 1.21.1
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A decorative Menorah item that shows a tooltip.
 */
public class MenorahItem extends Item {
    public MenorahItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        // This key "item.simchacraft.menorah.tooltip" must be in your en_us.json
        tooltip.add(Text.translatable(this.getTranslationKey() + ".tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}