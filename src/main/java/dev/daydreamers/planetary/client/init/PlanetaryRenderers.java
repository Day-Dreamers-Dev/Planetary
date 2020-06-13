package dev.daydreamers.planetary.client.init;

import dev.daydreamers.planetary.client.render.RocketRenderer;
import dev.daydreamers.planetary.init.*;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class PlanetaryRenderers {
    public static void register() {
        EntityRendererRegistry.INSTANCE.register(PlanetaryEntities.ROCKET, (dispatcher, context) -> new RocketRenderer(dispatcher));
    }
}
