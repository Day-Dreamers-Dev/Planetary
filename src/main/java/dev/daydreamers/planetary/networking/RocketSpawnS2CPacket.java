package dev.daydreamers.planetary.networking;

import dev.daydreamers.planetary.client.PlanetaryClient;
import dev.daydreamers.planetary.entity.RocketEntity;
import dev.daydreamers.planetary.init.PlanetaryEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RocketSpawnS2CPacket implements Packet<ClientPlayPacketListener>  {
    private int id;
    private UUID uuid;
    private double x;
    private double y;
    private double z;
    private EntityType<?> entityTypeId;
    private final HashMap<Vec3i, BlockState> rocketBlocks;

    public RocketSpawnS2CPacket(int id, UUID uuid, double x, double y, double z,  HashMap<Vec3i, BlockState> rocketBlocks) {
        this.id = id;
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityTypeId = PlanetaryEntities.ROCKET;
        this.rocketBlocks = rocketBlocks;
    }

    public RocketSpawnS2CPacket(RocketEntity entity) {
        this(entity.getEntityId(), entity.getUuid(), entity.getX(), entity.getY(), entity.getZ(), entity.rocketBlocks);
    }

    public void read(PacketByteBuf buf) {
        this.id = buf.readVarInt();
        this.uuid = buf.readUuid();
        this.entityTypeId = Registry.ENTITY_TYPE.get(buf.readVarInt());
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();

        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            this.rocketBlocks.put(buf.readBlockPos(), Block.STATE_IDS.get(buf.readVarInt()));
        }
    }

    public void write(PacketByteBuf buf) {
        buf.writeVarInt(this.id);
        buf.writeUuid(this.uuid);
        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(this.entityTypeId));
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeVarInt(rocketBlocks.size());
        for (Map.Entry<Vec3i, BlockState> rocketBlock : rocketBlocks.entrySet()) {
            buf.writeBlockPos(new BlockPos(rocketBlock.getKey()));
            buf.writeVarInt(Block.getRawIdFromState(rocketBlock.getValue()));
        }
    }

    public void apply(ClientPlayPacketListener clientPlayPacketListener) {
        //PlanetaryClient.handleRocketSpawn(this);
    }

    @Environment(EnvType.CLIENT)
    public int getId() {
        return this.id;
    }

    @Environment(EnvType.CLIENT)
    public UUID getUuid() {
        return this.uuid;
    }

    @Environment(EnvType.CLIENT)
    public double getX() {
        return this.x;
    }

    @Environment(EnvType.CLIENT)
    public double getY() {
        return this.y;
    }

    @Environment(EnvType.CLIENT)
    public double getZ() {
        return this.z;
    }

    @Environment(EnvType.CLIENT)
    public HashMap<Vec3i, BlockState> getRocketBlocks() { return this.rocketBlocks; }
}
