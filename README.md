# SimchaCraft — a small, goofy, respectful Minecraft mod

This is a Fabric mod for Minecraft 1.21.1 that adds custom items and entities, including the **Maisa Creeper** — a custom creeper entity with unique textures and sound effects, plus the original lighthearted items like the Menorah, Challah, and Shofar.

## Features

- **Maisa Creeper** — A custom creeper entity with custom textures and a high-pitched pop sound when exploding.
- **Maisa Spawn Egg** — Spawn eggs to create Maisa Creepers in-game.
- **Menorah** — A decorative item celebrating Jewish cultural heritage.
- **Challah** — A tasty-looking bread item.
- **Shofar** — A playful item that plays a sound and gives a brief Strength effect.
 - **Nevo Nahmias** — A new teenager NPC/entity who carries a vape item and inhales occasionally (produces smoke particles and sound; applies Nausea+Poison effects).
 - **Vape item** — A handheld vape that players (and Nevo) can use. Using it produces smoke particles, plays a custom inhale sound, and applies Nausea and Poison for ~5 seconds. A custom OGG sound file is required to enable the inhale SFX.

## Notes and assumptions

- **Target:** Fabric 0.15.11 for Minecraft 1.21.1
- **Java:** Java 21 is required
- **Build System:** Gradle with Fabric Loom

## Key Files

- `src/main/java/com/example/simchacraft/SimchaCraftMod.java` — main mod initializer, item/entity/sound registrations
- `src/main/java/com/example/simchacraft/MaisaCreeper.java` — custom creeper entity with sound integration
- `src/main/java/com/example/simchacraft/MaisaSpawnEggItem.java` — spawn egg for Maisa Creepers
- `src/main/java/com/example/simchacraft/SimchaCraftClient.java` — client-side entity renderer registration
 - `src/main/java/com/example/simchacraft/NevoNahmiasEntity.java` — the Nevo entity with inhale behavior and erratic wandering AI
 - `src/main/java/com/example/simchacraft/VapeItem.java` — the vape item implementation (particles, sound, effects)
 - `src/main/java/com/example/simchacraft/NevoSpawnEggItem.java` — spawn egg for Nevo Nahmias
 - `src/main/resources/assets/simchacraft/sounds.json` — sound registry mapping; expects `sounds/vape_inhale.ogg`
- `src/main/resources/fabric.mod.json` — mod metadata
- `src/main/resources/simchacraft.mixins.json` — Mixin configuration
- `src/main/resources/assets/simchacraft/textures/entity/maisa_creeper.png` — Maisa Creeper texture
- `src/main/resources/assets/simchacraft/models/item/` — Item model JSONs
- `build.gradle`, `settings.gradle` — Gradle build configuration

## How to Build and Run

1. **Prerequisites:**
   - Java 21 installed
   - Gradle wrapper is included (`gradlew.bat` for Windows, `gradlew` for Unix)

2. **Build the mod:**
   ```bash
   ./gradlew clean build  # Unix/Mac
   gradlew.bat clean build  # Windows
   ```

3. **Run the development client:**
   ```bash
   ./gradlew runClient  # Unix/Mac
   gradlew.bat runClient  # Windows
   ```

4. **Locate the JAR file:**
   After building, the compiled mod JAR will be at `build/libs/simchacraft-1.0.0.jar`

5. **Install the mod:**
## Assets you must add

- Place the Nevo texture at:
   `src/main/resources/assets/simchacraft/textures/entity/nevo_nahmias.png`
- Place the vape item texture at:
   `src/main/resources/assets/simchacraft/textures/item/vape.png`
- Place the spawn egg texture at:
   `src/main/resources/assets/simchacraft/textures/item/nevo_spawn_egg.png`
- Place the custom inhale sound at:
   `src/main/resources/assets/simchacraft/sounds/vape_inhale.ogg`

After adding these assets, re-run the dev client to verify visuals and audio.

## Git & Push (quick reminder)

Create a feature branch and push your changes to GitHub so you can open a PR:

```powershell
cd C:\Development\simchacraft\simchacraft
git checkout -b feature/nevo-vape
git add -A
git commit -m "Add Nevo Nahmias entity, vape item, renderer, sounds and spawn egg"
git push -u origin feature/nevo-vape
```

Then open a Pull Request on GitHub and run your CI/dev client as desired.

   - Place `simchacraft-1.0.0.jar`(located in simchacraft/build/libs) in your Minecraft `mods` folder
   - Ensure you have Fabric Loader 0.15.11+ and Fabric API installed
   - Launch Minecraft with the Fabric profile

## Respectful Content Note

This mod is intended to be playful and respectful. It celebrates cultural elements in a small, tasteful way while maintaining lighthearted gameplay mechanics.
