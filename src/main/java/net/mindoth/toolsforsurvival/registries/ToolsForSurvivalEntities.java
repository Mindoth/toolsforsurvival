package net.mindoth.toolsforsurvival.registries;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.entity.ThrownJavelinEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ToolsForSurvivalEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ToolsForSurvival.MOD_ID);

    public static final RegistryObject<EntityType<ThrownJavelinEntity>> THROWN_JAVELIN =
            ENTITIES.register("thrown_javelin", () -> registerEntity(EntityType.Builder.of(ThrownJavelinEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F).setCustomClientFactory(ThrownJavelinEntity::new), "thrown_javelin"));

    private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.build(entityName);
    }
}
