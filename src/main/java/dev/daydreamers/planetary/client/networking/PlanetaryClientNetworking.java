package dev.daydreamers.planetary.client.networking;

import dev.daydreamers.planetary.entity.RocketEntity;
import dev.daydreamers.planetary.init.PlanetaryEntities;
import dev.daydreamers.planetary.networking.PlanetaryNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.UUID;

public class PlanetaryClientNetworking {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(PlanetaryNetworking.ROCKET_SPAWN, (client, handler, buf, responseSender) -> {
            int id = buf.readVarInt();
            UUID uuid = buf.readUuid();
            EntityType<?> entityTypeId = Registry.ENTITY_TYPE.get(buf.readVarInt());

            double x = buf.readDouble();
            double y = buf.readDouble();
            double z = buf.readDouble();

            HashMap<Vec3i, BlockState> rocketBlocks = new HashMap<>();

            int length = buf.readVarInt();
            for (int i = 0; i < length; i++) {
                System.out.println();
                rocketBlocks.put(buf.readBlockPos(), Block.STATE_IDS.get(buf.readVarInt()));
            }

            client.execute(() -> {
                System.out.println(client.world);
                System.out.println(handler.getWorld());

                RocketEntity entity = new RocketEntity(PlanetaryEntities.ROCKET, client.world);

                entity.setVelocity(Vec3d.ZERO);
                entity.updatePosition(x, y, z);
                entity.updateTrackedPosition(x, y, z);

                entity.pitch = 0;
                entity.yaw = 0;
                entity.rocketBlocks.putAll(rocketBlocks);

                entity.setUuid(uuid);
                entity.setEntityId(id);
                client.world.addEntity(id, entity);
            });
        });
    }
}
