package com.example.simchacraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class SimchaCraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register the entity renderer for MaisaCreeper
        EntityRendererRegistry.register(SimchaCraftMod.MAISA_CREEPER, (context) -> {
            return new CreeperEntityRenderer(context);
        });
    }
}