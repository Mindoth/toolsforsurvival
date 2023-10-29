package net.mindoth.toolsforsurvival.item;

import net.mindoth.toolsforsurvival.ToolsForSurvival;
import net.mindoth.toolsforsurvival.registries.ToolsForSurvivalItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ToolsForSurvival.MOD_ID)
public class BowDrillItem extends Item {

    public BowDrillItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 100;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.pass(itemstack);
    }

    private static Direction getHorizontalDirection(Player player) {
        return player == null ? Direction.NORTH : player.getDirection();
    }

    @SubscribeEvent
    public static void duringItemUse(final LivingEntityUseItemEvent.Tick event) {
        if ( event.getItem().getItem() != ToolsForSurvivalItems.BOW_DRILL.get() ) return;
        if ( !(event.getEntity() instanceof Player player) ) return;
        if ( event.getDuration() == 1 && !player.getLevel().isClientSide ) event.getItem().hurtAndBreak(1, event.getEntity(), (holder) -> holder.broadcastBreakEvent(event.getEntity().getUsedItemHand()));

        HitResult result = player.pick(4.5D, 0.0f, false);
        if ( result.getType() != HitResult.Type.BLOCK ) return;
        BlockPos blockPos = ((BlockHitResult)result).getBlockPos();

        if ( !(player.getLevel() instanceof ServerLevel level) ) return;
        level.sendParticles(ParticleTypes.SMOKE, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, 1, 0, 0, 0, 0);
    }

    @SubscribeEvent
    public static void itemUseFinish(final LivingEntityUseItemEvent.Finish event) {
        if ( event.getItem().getItem() != ToolsForSurvivalItems.BOW_DRILL.get() ) return;
        if ( !(event.getEntity() instanceof Player player) ) return;
        Level level = player.getLevel();
        if ( level.isClientSide ) return;
        ItemStack itemstack = event.getItem();
        if ( itemstack.getItem() != ToolsForSurvivalItems.BOW_DRILL.get() ) return;
        
        HitResult result = player.pick(4.5D, 0.0f, false);
        if ( result.getType() != HitResult.Type.BLOCK ) return; 
        BlockPos blockPos = ((BlockHitResult)result).getBlockPos();
        BlockState blockState = player.level.getBlockState(blockPos);
        Direction face = ((BlockHitResult) result).getDirection();

        if ( !CampfireBlock.canLight(blockState) && !CandleBlock.canLight(blockState) && !CandleCakeBlock.canLight(blockState) ) {
            BlockPos blockpos1 = blockPos.relative(face);
            if ( BaseFireBlock.canBePlacedAt(level, blockpos1, getHorizontalDirection(player)) ) {
                level.playSound(null, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockState1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockState1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockpos1, itemstack);
            }
        }
        else {
            level.playSound(null, blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockPos, blockState.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
        }
    }
}
