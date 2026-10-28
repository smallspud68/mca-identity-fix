package dev.ps5.mcaidentity.mixin;

import archives.tater.gentlyholds.EntityItem;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.ps5.mcaidentity.Identity;
import net.conczin.mca.entity.VillagerLike;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Gently Holds drops "UUID" when a mob becomes an item, so every villager it puts down is a stranger to MCA.
 * Give it back the UUID it was picked up with, unless that villager somehow still exists.
 */
@Mixin(value = EntityItem.class, remap = false)
public abstract class GentlyHoldsEntityItemMixin {
    @WrapMethod(method = "entityOf(Lnet/minecraft/world/item/component/TypedEntityData;Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;")
    private static Entity mcaidentity$restoreUuid(TypedEntityData<EntityType<?>> data, Level level, Operation<Entity> original) {
        boolean outer = Identity.BUILDING_COPY.get();
        Identity.BUILDING_COPY.set(true);
        Entity entity;
        try {
            entity = original.call(data, level);
        } finally {
            Identity.BUILDING_COPY.set(outer);
        }
        if (entity instanceof VillagerLike<?> && level instanceof ServerLevel serverLevel) {
            Identity.read(data.getUnsafe())
                .filter(id -> serverLevel.getEntity(id) == null)
                .ifPresent(entity::setUUID);
        }
        return entity;
    }
}
