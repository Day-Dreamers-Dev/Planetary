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
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlanetaryItems
{
    public static final List<Runnable> registrants = new ArrayList<>();

    public static Item makeItem(String name, Item item)
    {
        registrants.add(() -> Registry.register(Registry.ITEM, new Identifier(Planetary.MOD_ID, name), item));
        return item;
    }

    public static void register()
    {
        registrants.forEach(Runnable::run);
    }
}
