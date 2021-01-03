/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 */
package dev.daydreamers.planetary.entity;

import dev.daydreamers.planetary.init.PlanetaryEntities;
import dev.daydreamers.planetary.networking.PlanetaryNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class RocketEntity extends Entity {
    public final HashMap<Vec3i, BlockState> rocketBlocks = new HashMap<>();

    public RocketEntity(EntityType<RocketEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag tag) {
        int length = tag.getInt("Length");

        for (int i = 0; i < length; i++) {
            int[] pos = tag.getIntArray("Pos" + i);
            this.rocketBlocks.put(new Vec3i(pos[0], pos[1], pos[2]), Block.STATE_IDS.get(tag.getInt("Block" + i)));
        }
    }

    @Override
    protected void writeCustomDataToTag(CompoundTag tag) {
        tag.putInt("Length", rocketBlocks.size());
        int i = 0;
        for (Map.Entry<Vec3i, BlockState> rocketBlock : rocketBlocks.entrySet()) {
            tag.putIntArray("Pos" + i, new int[]{rocketBlock.getKey().getX(), rocketBlock.getKey().getY(), rocketBlock.getKey().getZ()});
            tag.putInt("Block" + i, Block.getRawIdFromState(rocketBlock.getValue()));
            i++;
        }
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick() {
        super.tick();
        this.setPos(this.getX(), this.getY() + 0.1, this.getZ());
    }

    protected boolean canClimb() {
        return false;
    }

    public boolean isCollidable() {
        return true;
    }

    public boolean isPushable() {
        return false;
    }

    public boolean collidesWith(Entity other) {
        return method_30959(this, other);
    }

    public static boolean method_30959(Entity entity, Entity entity2) {
        return (entity2.isCollidable() || entity2.isPushable()) && !entity.isConnectedThroughVehicle(entity2);
    }

    @Override
    public void pushAwayFrom(Entity entity) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeVarInt(this.getEntityId());
        buf.writeUuid(this.getUuid());
        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(PlanetaryEntities.ROCKET));
        buf.writeDouble(this.getX());
        buf.writeDouble(this.getY());
        buf.writeDouble(this.getZ());
        buf.writeVarInt(rocketBlocks.size());
        for (Map.Entry<Vec3i, BlockState> rocketBlock : rocketBlocks.entrySet()) {
            buf.writeBlockPos(new BlockPos(rocketBlock.getKey()));
            buf.writeVarInt(Block.getRawIdFromState(rocketBlock.getValue()));
        }

        return ServerSidePacketRegistryImpl.INSTANCE.toPacket(PlanetaryNetworking.ROCKET_SPAWN, buf);
    }
}
