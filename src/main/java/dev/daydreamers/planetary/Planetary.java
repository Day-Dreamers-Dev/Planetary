/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 */
package dev.daydreamers.planetary;

import dev.daydreamers.planetary.init.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Planetary implements ModInitializer
{
    public static final String MOD_ID = "planetary";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "itemgroup"),
            () -> new ItemStack(PlanetaryBlocks.SILKWOOD_LOG)
    );

    @Override
    public void onInitialize()
    {
        PlanetaryBlocks.register();
        PlanetaryItems.register();
        PlanetaryEntities.register();
    }
}
