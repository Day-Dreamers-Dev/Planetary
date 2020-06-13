/*
 * Copyright 2020 Day Dreamers Dev
 *
 * Planetary is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * https://www.gnu.org/licenses/
 */
package dev.daydreamers.planetary.client.render;

import dev.daydreamers.planetary.Planetary;
import dev.daydreamers.planetary.entity.RocketEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3i;

import java.util.Iterator;
import java.util.Map;

public class RocketRenderer extends EntityRenderer<RocketEntity> {
    public RocketRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
        this.shadowRadius = 0.7F;
    }

    @Override
    public Identifier getTexture(RocketEntity entity) {
        return new Identifier(Planetary.MOD_ID, "test");
    }

    @Override
    public void render(RocketEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        matrices.push();
        matrices.translate(-0.5, 0, -0.5);

        for (Map.Entry<Vec3i, BlockState> pair : entity.rocketBlocks.entrySet()) {
            matrices.push();

            Vec3i pos = pair.getKey();

            matrices.translate(pos.getX(), pos.getY(), pos.getZ());
            MinecraftClient.getInstance().getBlockRenderManager().renderBlockAsEntity(pair.getValue(), matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);

            matrices.pop();
        }

        matrices.pop();
    }
}
