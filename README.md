# MCA Villager Identity Fix

A small Fabric addon for [MCA Reborn](https://github.com/Luke100000/minecraft-comes-alive) (Minecraft Comes Alive) that keeps MCA villagers' identities intact when they are picked up with [Gently Holds](https://modrinth.com/mod/gently-holds).

## What it does

- Preserves an MCA villager's UUID when it is picked up and placed back down, so its family, marriages, and village home survive the round-trip instead of being reset.
- Stops Gently Holds' held-villager sounds from registering phantom family-tree entries, which otherwise fills the MCA family tree with duplicates.
- Stops Gently Holds from crashing the game while rendering a held [GeckoLib](https://modrinth.com/mod/geckolib) mob (uses the combined `createRenderState`).

## Requirements

- Minecraft 26.1.2 (Fabric)
- [MCA Reborn](https://github.com/Luke100000/minecraft-comes-alive) `8.1.11+26.1.2`
- [Gently Holds](https://modrinth.com/mod/gently-holds) (suggested — the fixes only matter when it is installed)

## Building

The project compiles against MCA and Gently Holds, which are not redistributed here. Copy the jars listed in [`libs/README.md`](libs/README.md) into `libs/`, then:

```
./gradlew build
```

The built jar lands in `build/libs/`.

## License

Licensed under the [GNU General Public License v3.0](LICENSE).

This is an independent addon that depends on and extends **Minecraft Comes Alive (MCA Reborn)** by Luke100000 and contributors, which is itself licensed under GPL-3.0. MCA is not included in this repository; see the [MCA source](https://github.com/Luke100000/minecraft-comes-alive). This project is not affiliated with or endorsed by the MCA team.
