package com.example.simchacraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.item.ItemGroups;
import com.example.simchacraft.VapeItem;
import com.example.simchacraft.NevoNahmiasEntity;

public class SimchaCraftMod implements ModInitializer {
	public static final String MOD_ID = "simchacraft";

	// --- ITEM DEFINITIONS ---
	public static final Item MENORAH = new MenorahItem(new Item.Settings().maxCount(1));
	public static final Item CHALLAH = new ChallahItem(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build()));
	public static final Item SHOFAR = new ShofarItem(new Item.Settings().maxCount(1));
	public static final Item MAISA_SPAWN_EGG = new MaisaSpawnEggItem(new Item.Settings().maxCount(1));
	public static final Item NEVO_SPAWN_EGG = new NevoSpawnEggItem(new Item.Settings().maxCount(1));
	// New vape item (single-use handheld vape device)
	public static final Item VAPE = new VapeItem(new Item.Settings().maxCount(1));

	// --- SOUND EVENT DEFINITIONS ---
	// Construct SoundEvent instances via the factory method available in mappings
	public static final SoundEvent SHOFAR_BLOW_EVENT = SoundEvent.of(Identifier.of(MOD_ID, "shofar_blow"));
	public static final SoundEvent MAISA_POP_EVENT = SoundEvent.of(Identifier.of(MOD_ID, "maisa_pop"));
    // Vape inhale sound (custom .ogg should be placed in assets/simchacraft/sounds/vape_inhale.ogg)
    public static final SoundEvent VAPE_INHALE_EVENT = SoundEvent.of(Identifier.of(MOD_ID, "vape_inhale"));

	// --- ENTITY DEFINITION ---
	public static final EntityType<MaisaCreeper> MAISA_CREEPER = Registry.register(
		Registries.ENTITY_TYPE,
		Identifier.of(MOD_ID, "maisa_creeper"),
		EntityType.Builder.<MaisaCreeper>create(MaisaCreeper::new, SpawnGroup.MONSTER)
			.dimensions(0.6f, 1.7f)  // Same size as vanilla creeper 
			.build()
	);

	// New teen creature: Nevo Nahmias
	public static final EntityType<NevoNahmiasEntity> NEVO_NAHMIAS = Registry.register(
		Registries.ENTITY_TYPE,
		Identifier.of(MOD_ID, "nevo_nahmias"),
		EntityType.Builder.<NevoNahmiasEntity>create(NevoNahmiasEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6f, 1.8f)
			.build()
	);

	public void onInitialize() {
		// --- ITEM REGISTRATION ---
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "menorah"), MENORAH);
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "challah"), CHALLAH);
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "shofar"), SHOFAR);
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "maisa_spawn_egg"), MAISA_SPAWN_EGG);
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "nevo_spawn_egg"), NEVO_SPAWN_EGG);
	Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "vape"), VAPE);

		// --- ENTITY ATTRIBUTE REGISTRATION ---
	FabricDefaultAttributeRegistry.register(MAISA_CREEPER, CreeperEntity.createCreeperAttributes());
	// Register attributes for Nevo Nahmias
	FabricDefaultAttributeRegistry.register(NEVO_NAHMIAS, NevoNahmiasEntity.createNevoAttributes());
		
		// --- SOUND REGISTRATION ---
	Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "shofar_blow"), SHOFAR_BLOW_EVENT);
	Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "maisa_pop"), MAISA_POP_EVENT);
	Registry.register(Registries.SOUND_EVENT, Identifier.of(MOD_ID, "vape_inhale"), VAPE_INHALE_EVENT);

		// --- CREATIVE TAB REGISTRATION (All items in Ingredient Tab) ---
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
			content.prepend(CHALLAH);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
			content.prepend(SHOFAR);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.prepend(MENORAH);
			content.prepend(MAISA_SPAWN_EGG);
			content.prepend(VAPE);
			content.prepend(NEVO_SPAWN_EGG);
		});
	}
}