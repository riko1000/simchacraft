package com.example.simchacraft;

import net.minecraft.client.render.entity.CreeperEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;

/**
 * Custom renderer for Maisa Creeper that applies the custom texture.
 */
public class MaisaCreeperRenderer extends CreeperEntityRenderer {
    private static final Identifier MAISA_TEXTURE = 
        Identifier.of(SimchaCraftMod.MOD_ID, "textures/entity/maisa_creeper.png");

    public MaisaCreeperRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(CreeperEntity entity) {
        return MAISA_TEXTURE;
    }
}
