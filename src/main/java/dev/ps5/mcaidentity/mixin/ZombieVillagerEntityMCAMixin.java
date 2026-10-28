package dev.ps5.mcaidentity.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.ps5.mcaidentity.Identity;
import net.conczin.mca.entity.ZombieVillagerEntityMCA;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ZombieVillagerEntityMCA.class, remap = false)
public abstract class ZombieVillagerEntityMCAMixin {
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void mcaidentity$save(ValueOutput output, CallbackInfo ci) {
        Identity.write(output, ((ZombieVillagerEntityMCA) (Object) this).getUUID());
    }

    /** Naming a throwaway copy would create a family tree entry under its random UUID. */
    @WrapOperation(method = "setCustomName", at = @At(value = "INVOKE",
        target = "Lnet/conczin/mca/entity/ZombieVillagerEntityMCA;setName(Ljava/lang/String;)V"))
    private void mcaidentity$skipCopyName(ZombieVillagerEntityMCA self, String name, Operation<Void> original) {
        if (!Identity.BUILDING_COPY.get()) {
            original.call(self, name);
        }
    }
}
