# Test Mod

A [NeoForge](https://neoforged.net/) mod for **Minecraft 1.21.1**.

| Item | Value |
| --- | --- |
| Minecraft | 1.21.1 |
| NeoForge | 21.1.264 |
| Mod ID | `mingmod` |
| Java | 21 |
| Gradle | 9.2.1 (wrapper) |
| ModDevGradle | 2.0.148 |

## Build

```bash
./gradlew build          # jar -> build/libs/mingmod-<version>.jar
./gradlew runClient      # launch a dev client
./gradlew runServer      # launch a dev server
./gradlew runData        # run data generation -> src/generated/resources
```

The build requires a JDK 21. Either set `JAVA_HOME` to a JDK 21 install, or let
Gradle resolve a toolchain automatically.

## Layout

```
src/main/java/com/xjkoharu/mingmod/   Java sources (package = mod_group_id)
src/main/resources/                   assets, data, lang files
src/main/templates/META-INF/          neoforge.mods.toml template (properties expanded at build time)
src/generated/resources/              data generator output (committed)
```

Version and metadata live in `gradle.properties`; `neoforge.mods.toml` is a
template whose `${...}` placeholders are filled from that file.

## Notes for contributors in mainland China

`maven.neoforged.net` is often unreachable. A **machine-local** Gradle init
script can redirect it to a reachable mirror; see `docs/network-zh.md`. Nothing
in this repository hardcodes a mirror URL.

## License

All Rights Reserved. See `LICENSE`.
