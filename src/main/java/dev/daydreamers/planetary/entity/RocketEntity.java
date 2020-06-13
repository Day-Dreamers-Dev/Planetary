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

    public RocketEntity(EntityType<MobEntity> type, World world) {
        super(type, world);

        rocketBlocks.put(new Vec3i(0, 0, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
        rocketBlocks.put(new Vec3i(0, 0, 1), Blocks.STONE.getDefaultState());
        rocketBlocks.put(new Vec3i(1, 0, 1), Blocks.STONE.getDefaultState());
        rocketBlocks.put(new Vec3i(0, 1, 1), Blocks.STONE.getDefaultState());
    }
}
