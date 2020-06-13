/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 */
package dev.daydreamers.planetary.init;

import dev.daydreamers.planetary.Planetary;
import dev.daydreamers.planetary.block.ThinLogBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlanetaryBlocks
{
    public static final List<Runnable> registrants = new ArrayList<>();

    public static final Block SILKWOOD_LOG = makeBlock("silkwood_log", new ThinLogBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block STRIPPED_SILKWOOD_LOG = makeBlock("stripped_silkwood_log", new ThinLogBlock(AbstractBlock.Settings.copy(SILKWOOD_LOG)));
    public static final Block SILKWOOD_LEAVES = makeBlock("silkwood_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
    public static final Block SILKWOOD_PLANKS = makeBlock("silkwood_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block SILKWOOD_SLAB = makeBlock("silkwood_slab", new SlabBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_STAIRS = makeBlock("silkwood_stairs", new PubStairsBlock(SILKWOOD_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_PRESSURE_PLATE = makeBlock("silkwood_pressure_plate", new PubPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_FENCE = makeBlock("silkwood_fence", new FenceBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_TRAPDOOR = makeBlock("silkwood_trapdoor", new PubTrapdoorBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_FENCE_GATE = makeBlock("silkwood_fence_gate", new FenceGateBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_BUTTON = makeBlock("silkwood_button", new PubWoodButtonBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    public static final Block SILKWOOD_DOOR = makeBlock("silkwood_door", new PubDoorBlock(AbstractBlock.Settings.copy(SILKWOOD_PLANKS)));
    // TODO: Sapling, Boat, and sign

    public static final Block SPIREROCK = makeBlock("spirerock", new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block SPIREROCK_WALL = makeBlock("spirerock_wall", new WallBlock(AbstractBlock.Settings.copy(SPIREROCK)));
    public static final Block SPIREROCK_SLAB = makeBlock("spirerock_slab", new SlabBlock(AbstractBlock.Settings.copy(SPIREROCK)));
    public static final Block SPIREROCK_STAIRS = makeBlock("spirerock_stairs", new PubStairsBlock(SPIREROCK.getDefaultState(), AbstractBlock.Settings.copy(SPIREROCK)));
    public static final Block SPIREROCK_BRICKS = makeBlock("spirerock_bricks", new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS)));
    public static final Block SPIREROCK_BRICK_WALL = makeBlock("spirerock_brick_wall", new WallBlock(AbstractBlock.Settings.copy(SPIREROCK_BRICKS)));
    public static final Block SPIREROCK_BRICK_SLAB = makeBlock("spirerock_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(SPIREROCK_BRICKS)));
    public static final Block SPIREROCK_BRICK_STAIRS = makeBlock("spirerock_brick_stairs", new PubStairsBlock(SPIREROCK_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SPIREROCK_BRICKS)));

    public static Block makeBlock(String name, Block block, Item.Settings settings)
    {
        registrants.add(() -> {
            Registry.register(Registry.BLOCK, new Identifier("planetary", name), block);

            if(settings != null)
                Registry.register(Registry.ITEM, new Identifier("planetary", name), new BlockItem(block, settings));
        });
        return block;
    }

    public static Block makeBlock(String name, Block block)
    {
        return makeBlock(name, block, new Item.Settings().group(Planetary.ITEM_GROUP));
    }

    public static void register()
    {
        registrants.forEach(Runnable::run);
    }

    // I know these could be done with an access widener... but I don't know if that would be better :thonk:
    public static class PubStairsBlock extends StairsBlock
    {
        public PubStairsBlock(BlockState baseBlockState, Settings settings)
        {
            super(baseBlockState, settings);
        }
    }

    public static class PubPressurePlateBlock extends PressurePlateBlock
    {
        public PubPressurePlateBlock(ActivationRule type, Settings settings)
        {
            super(type, settings);
        }
    }

    public static class PubTrapdoorBlock extends TrapdoorBlock
    {
        public PubTrapdoorBlock(Settings settings)
        {
            super(settings);
        }
    }

    public static class PubWoodButtonBlock extends WoodButtonBlock
    {
        public PubWoodButtonBlock(Settings settings)
        {
            super(settings);
        }
    }

    public static class PubDoorBlock extends DoorBlock
    {
        public PubDoorBlock(Settings settings)
        {
            super(settings);
        }
    }
}
