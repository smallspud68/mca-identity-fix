package dev.ps5.mcaidentity.mixin;

import archives.tater.gentlyholds.client.EntityCache;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Gently Holds builds a held mob's render state with createRenderState() + extractRenderState(). GeckoLib's
 * createRenderState() is deliberately null (it only supports the combined createRenderState(entity, partialTicks)),
 * so drawing a held GeckoLib mob crashed the game. Use the combined method, and draw nothing rather than crash.
 */
@Mixin(value = EntityCache.class, remap = false)
public abstract class GentlyHoldsEntityCacheMixin {
    @WrapMethod(method = "createRenderState(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/entity/EntityRenderer;)Lnet/minecraft/client/renderer/entity/state/EntityRenderState;")
    private static <T extends Entity, S extends EntityRenderState> S mcaidentity$combinedRenderState(T entity, EntityRenderer<T, S> renderer, Operation<S> original) {
        try {
            return renderer.createRenderState(entity, 1.0F);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
