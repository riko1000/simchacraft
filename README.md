# SimchaCraft — a small, goofy, respectful Minecraft mod

This is a Fabric mod for Minecraft 1.21.1 that adds custom items and entities, including the **Maisa Creeper** — a custom creeper entity with unique textures and sound effects, plus the original lighthearted items like the Menorah, Challah, and Shofar.

## Features

- **Maisa Creeper** — A custom creeper entity with custom textures and a high-pitched pop sound when exploding.
- **Maisa Spawn Egg** — Spawn eggs to create Maisa Creepers in-game.
- **Menorah** — A decorative item celebrating Jewish cultural heritage.
- **Challah** — A tasty-looking bread item.
- **Shofar** — A playful item that plays a sound and gives a brief Strength effect.

## Notes and assumptions

- **Target:** Fabric 0.15.11 for Minecraft 1.21.1
- **Java:** Java 21 is required
- **Build System:** Gradle with Fabric Loom

## Key Files

- `src/main/java/com/example/simchacraft/SimchaCraftMod.java` — main mod initializer, item/entity/sound registrations
- `src/main/java/com/example/simchacraft/MaisaCreeper.java` — custom creeper entity with sound integration
- `src/main/java/com/example/simchacraft/MaisaSpawnEggItem.java` — spawn egg for Maisa Creepers
- `src/main/java/com/example/simchacraft/SimchaCraftClient.java` — client-side entity renderer registration
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
   - Place `simchacraft-1.0.0.jar`(located in simchacraft/build/libs) in your Minecraft `mods` folder
   - Ensure you have Fabric Loader 0.15.11+ and Fabric API installed
   - Launch Minecraft with the Fabric profile

## Respectful Content Note

This mod is intended to be playful and respectful. It celebrates cultural elements in a small, tasteful way while maintaining lighthearted gameplay mechanics.
