/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 *
 * File: Planetary.java
 * Date: 2020-06-11 "Basic Fabric Setup"
 */
package dev.daydreamers.planetary;

import dev.daydreamers.planetary.init.*;
import net.fabricmc.api.ModInitializer;

public class Planetary implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        PlanetaryBlocks.register();
        PlanetaryItems.register();
        PlanetaryEntities.register();
    }
}
