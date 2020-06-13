/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 */
package dev.daydreamers.planetary.client.init;

import dev.daydreamers.planetary.client.render.RocketRenderer;
import dev.daydreamers.planetary.init.*;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class PlanetaryRenderers {
    public static void register() {
        EntityRendererRegistry.INSTANCE.register(PlanetaryEntities.ROCKET, (dispatcher, context) -> new RocketRenderer(dispatcher));
    }
}
