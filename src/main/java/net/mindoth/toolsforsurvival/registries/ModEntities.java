package net.mindoth.toolsforsurvival.registries;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.entity.ThrownJavelinEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, ToolsForSurvival.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownJavelinEntity>> THROWN_JAVELIN
            = registerEntity(EntityType.Builder.<ThrownJavelinEntity>of(ThrownJavelinEntity::new,
            MobCategory.MISC).sized(0.5F, 0.5F), "thrown_javelin");


    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> registerEntity(EntityType.Builder<T> builder, String entityName) {
        return ENTITIES.register(entityName, () -> builder.build(entityName));
    }
}
