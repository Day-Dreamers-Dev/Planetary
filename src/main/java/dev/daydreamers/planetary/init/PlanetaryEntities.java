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
import dev.daydreamers.planetary.entity.RocketEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlanetaryEntities {
    public static final List<Runnable> registrants = new ArrayList<>();

    public static final EntityType<RocketEntity> ROCKET = makeEntity(
            "rocket", RocketEntity::new, SpawnGroup.MISC, 10f, 1f,
            false, 0, 0, null
    );

    public static <T extends Entity> EntityType<T> makeEntity(
            String name, EntityType.EntityFactory<T> factory, SpawnGroup spawnGroup, float width, float height,
            boolean createSpawnEgg, int primaryColor, int secondaryColor, DefaultAttributeContainer.Builder attributes)
    {
        EntityType<T> entityType = FabricEntityTypeBuilder.create(spawnGroup, factory).dimensions(EntityDimensions.fixed(width, height)).build();

        registrants.add(() -> {
            System.out.println(name);
            Registry.register(Registry.ENTITY_TYPE, new Identifier(Planetary.MOD_ID, name), entityType);

            if(attributes != null)
                FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) entityType, attributes);

            if(createSpawnEgg)
                Registry.register(Registry.ITEM, new Identifier(Planetary.MOD_ID, name + "_spawn_egg"),
                        new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Settings().group(Planetary.ITEM_GROUP))
                );
        });

        return entityType;
    }

    public static void register() {
        registrants.forEach(Runnable::run);
    }
}
