package dev.ps5.mcaidentity;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.ValueOutput;

public final class Identity {
    /** Copy of the villager's UUID that mob-pickup mods don't strip (unlike "UUID"). */
    public static final String TAG = "MCAIdentity";

    /** Set while Gently Holds builds a throwaway copy of a held villager. */
    public static final ThreadLocal<Boolean> BUILDING_COPY = ThreadLocal.withInitial(() -> false);

    private Identity() {
    }

    public static void write(ValueOutput output, UUID uuid) {
        output.store(TAG, UUIDUtil.CODEC, uuid);
    }

    public static Optional<UUID> read(CompoundTag entityData) {
        return entityData.read(TAG, UUIDUtil.CODEC);
    }
}
