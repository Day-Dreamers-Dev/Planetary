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

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.HashMap;

//TODO: Convert to Entity instead of Mob
public class RocketEntity extends MobEntity {
    public final HashMap<Vec3i, BlockState> rocketBlocks = new HashMap<>();

    public RocketEntity(EntityType<RocketEntity> type, World world) {
        super(type, world);

        rocketBlocks.put(new Vec3i(0, 0, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
    }

    @Override
    public void tick() {
        super.tick();

        System.out.println("Bodasd");
    }
}
