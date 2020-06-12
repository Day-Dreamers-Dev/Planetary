package dev.daydreamers.planetary.client;

import dev.daydreamers.planetary.Planetary;
import dev.daydreamers.planetary.client.render.RocketRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class PlanetaryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //TODO Move this to a separate class
        EntityRendererRegistry.INSTANCE.register(Planetary.ROCKET, (dispatcher, context) -> new RocketRenderer(dispatcher));
    }
}
