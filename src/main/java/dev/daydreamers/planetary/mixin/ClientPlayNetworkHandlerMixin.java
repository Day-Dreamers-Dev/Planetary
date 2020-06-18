package dev.daydreamers.planetary.mixin;

import dev.daydreamers.planetary.entity.RocketEntity;
import dev.daydreamers.planetary.init.PlanetaryEntities;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow private ClientWorld world;

    @Inject(method = "onEntitySpawn(Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/network/packet/s2c/play/EntitySpawnS2CPacket;getEntityTypeId()Lnet/minecraft/entity/EntityType;"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo ci, double x, double y, double z, EntityType<?> type) {
        if (type == PlanetaryEntities.ROCKET) {
            RocketEntity entity = new RocketEntity(PlanetaryEntities.ROCKET, this.world);

            entity.setVelocity(Vec3d.ZERO);
            entity.updatePosition(x, y, z);
            entity.updateTrackedPosition(x, y, z);

            entity.pitch = 0;
            entity.yaw = 0;

            entity.setUuid(packet.getUuid());
            entity.setEntityId(packet.getId());
            this.world.addEntity(packet.getId(), entity);

            ci.cancel();
        }
    }
}
