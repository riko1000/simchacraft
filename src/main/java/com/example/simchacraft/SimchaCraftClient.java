package com.example.simchacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
// imports for Nevo renderer are in NevoRenderer

public class SimchaCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register the custom renderer for MaisaCreeper with the custom texture
        EntityRendererRegistry.register(SimchaCraftMod.MAISA_CREEPER, (context) -> {
            return new MaisaCreeperRenderer(context);
        });

        // Register the dedicated Nevo renderer (handles held items correctly)
        EntityRendererRegistry.register(SimchaCraftMod.NEVO_NAHMIAS, (context) -> new NevoRenderer(context));
    }
}