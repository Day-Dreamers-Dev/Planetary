package dev.daydreamers.planetary.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlanetaryBlocks {
    public static final List<Runnable> registrants = new ArrayList<>();

    public static Block makeBlock(String name, Block block, boolean createItem, ItemGroup group) {
        registrants.add(() -> {
            Registry.register(Registry.BLOCK, new Identifier("planetary", name), block);

            if(createItem)
                Registry.register(Registry.ITEM, new Identifier("planetary", name), new BlockItem(block, new Item.Settings().group(ItemGroup.MISC)));
        });

        return block;
    }

    public static void register() {
        registrants.forEach(Runnable::run);
    }
}
