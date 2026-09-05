# EmberDeath

EmberDeath is a Paper plugin that applies configurable effects when a player dies.
It can notify nearby players, play a death sound, and kick or permanently ban the deceased player.

## Requirements

- Minecraft/Paper `1.16.5`, `1.17`, `1.18`, `1.19`, `1.20`, `1.21`, `26.1`, or `26.2`
- Java `8` or newer

## Features

- Local death messages with configurable radius and formatting.
- Configurable death sound, volume, pitch, and radius.
- `KICK` or permanent `BAN` punishment modes.
- Legacy `&` colors and HEX colors in the `&#RRGGBB` format.
- Validation and safe fallbacks for invalid mode and sound settings.
- Separate server-version adapter source files for every supported version.

## Installation

1. Build the plugin with `gradle --no-daemon clean build`.
2. Copy `build/libs/EmberDeath-1.1.2.jar` to the server's `plugins` directory.
3. Start the Paper server once to generate the configuration.
4. Edit `plugins/EmberDeath/config.yml` as needed.

## Configuration

The default configuration is available at `src/main/resources/config.yml`.

- `death-message`: controls nearby death messages and their format.
- `death-sound`: controls the sound played to nearby players.
- `punishment.enabled`: enables or disables punishment.
- `punishment.mode`: accepts `KICK` or `BAN`.
- `punishment.kick-reason` and `punishment.ban-reason`: customize disconnect text.

## Development

Run a clean build with:

```text
gradle --no-daemon clean build
```

The generated plugin JAR is placed in `build/libs/`.

The Gradle compile baseline is Spigot API `1.16.5`, which keeps the plugin's
bytecode compatible with Java 8. The runtime adapters use the shared Bukkit API
and are kept separately for each supported server version.
