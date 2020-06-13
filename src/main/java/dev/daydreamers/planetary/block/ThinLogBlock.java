package dev.daydreamers.planetary.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ThinLogBlock extends PillarBlock
{
    public static VoxelShape Y_SHAPE = createCuboidShape(1.0,0.0, 1.0, 15.0, 16.0, 15.0);
    public static VoxelShape X_SHAPE = createCuboidShape(0.0, 1.0, 1.0, 16.0, 15.0, 15.0);
    public static VoxelShape Z_SHAPE = createCuboidShape(1.0, 1.0, 0.0, 15.0, 15.0, 16.0);

    public ThinLogBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        Direction.Axis axis = state.get(AXIS);
        switch (axis)
        {
            case X:
                return X_SHAPE;
            case Z:
                return Z_SHAPE;
            default:
                return Y_SHAPE;
        }
    }
}
