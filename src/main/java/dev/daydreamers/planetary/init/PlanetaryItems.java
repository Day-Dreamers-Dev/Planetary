package dev.daydreamers.planetary.init;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class PlanetaryItems {
    public static final List<Runnable> registrants = new ArrayList<>();

    public static Item makeItem(String name, Item item) {
        registrants.add(() -> Registry.register(Registry.ITEM, new Identifier("planetary", name), item));

        return item;
    }

    public static void register() {
        registrants.forEach(Runnable::run);
    }
}
