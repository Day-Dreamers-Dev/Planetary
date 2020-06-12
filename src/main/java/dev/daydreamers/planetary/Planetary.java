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

import dev.daydreamers.planetary.entity.RocketEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Planetary implements ModInitializer
{
    //TODO Move this to a separate class
    public static final EntityType<MobEntity> ROCKET = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("planetary", "rocket"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RocketEntity::new).dimensions(EntityDimensions.fixed(1f, 1f)).build()
    );

    @Override
    public void onInitialize()
    {
        FabricDefaultAttributeRegistry.register(ROCKET, RocketEntity.createMobAttributes());
    }
}
