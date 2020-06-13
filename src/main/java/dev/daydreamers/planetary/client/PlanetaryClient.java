package dev.daydreamers.planetary.client;

import dev.daydreamers.planetary.client.init.PlanetaryRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PlanetaryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PlanetaryRenderers.register();
    }
}
