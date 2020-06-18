package dev.daydreamers.planetary.block.rocket;

import dev.daydreamers.planetary.Planetary;
import dev.daydreamers.planetary.entity.RocketEntity;
import dev.daydreamers.planetary.init.PlanetaryEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class RocketComputerBlock extends Block {

    public RocketComputerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        HashMap<BlockPos, BlockState> neighbors = getNeighbors(world, pos);
        HashMap<Vec3i, BlockState> offsetNeighbors = new HashMap<>();

        for (Map.Entry<BlockPos, BlockState> neighbor : neighbors.entrySet()) {
            System.out.println(neighbor.getKey().subtract(pos));
            offsetNeighbors.put(neighbor.getKey().subtract(pos), neighbor.getValue());
            world.setBlockState(neighbor.getKey(), Blocks.AIR.getDefaultState());
        }


        RocketEntity rocket = new RocketEntity(PlanetaryEntities.ROCKET, world);
        rocket.rocketBlocks.putAll(offsetNeighbors);
        world.spawnEntity(rocket);
    }

    public HashMap<BlockPos, BlockState> getNeighbors(World world, BlockPos pos) {
        HashMap<BlockPos, BlockState> blocks = new HashMap<>();

        try {
            recurse(world, pos, blocks);
        } catch (StackOverflowError error) {
            Planetary.LOGGER.warn("Attempted to scan rocket touching the ground!");
        }

        return blocks;
    }

    public void recurse(World world, BlockPos pos, HashMap<BlockPos, BlockState> blocks){
        for(Direction direction : Direction.values()){
            BlockPos position = pos.add(direction.getVector());

            BlockState blockState = world.getBlockState(position);
            if (!blocks.containsKey(position.toImmutable()) && blockState.getBlock() != Blocks.AIR) {
                blocks.put(position.toImmutable(), blockState);
                recurse(world, position.toImmutable(), blocks);
            }
        }
    }
}
