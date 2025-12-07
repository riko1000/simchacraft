package com.example.simchacraft;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

/**
 * Renderer for Nevo Nahmias that uses a player biped model and shows held items.
 */
public class NevoRenderer extends BipedEntityRenderer<NevoNahmiasEntity, BipedEntityModel<NevoNahmiasEntity>> {
    public NevoRenderer(Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.PLAYER)), 0.5f);
    // BipedEntityRenderer already handles arm poses; the held item should render if the renderer supports it
    }

    @Override
    public Identifier getTexture(NevoNahmiasEntity entity) {
        return Identifier.of(SimchaCraftMod.MOD_ID, "textures/entity/nevo_nahmias.png");
    }
}
