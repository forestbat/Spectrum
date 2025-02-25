package de.dafuqs.spectrum.blocks.mob_blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class LineTeleportingMobBlock extends MobBlock {
	
	protected final int range;
	
	public LineTeleportingMobBlock(Settings settings, int range) {
		super(settings);
		this.range = range;
	}
	
	@Override
	public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
		super.appendTooltip(stack, world, tooltip, options);
		tooltip.add(new TranslatableText( "block.spectrum.line_teleporting_mob_block.tooltip", range));
		tooltip.add(new TranslatableText( "block.spectrum.line_teleporting_mob_block.tooltip2", range));
	}
	
	@Override
	public boolean trigger(ServerWorld world, BlockPos blockPos, BlockState state, @Nullable Entity entity, Direction side) {
		if(entity != null) {
			Optional<BlockPos> foundBlockPos = searchForBlock(world, blockPos, state, side, this.range);
			if (foundBlockPos.isPresent()) {
				BlockPos targetPos = foundBlockPos.get().up();
				RandomTeleportingMobBlock.teleportTo(world, entity, targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5);
				return true;
			}
		}
		return false;
	}
	
	public static Optional<BlockPos> searchForBlock(World world, BlockPos pos, BlockState searchedState, Direction direction, int range) {
		BlockPos.Mutable mutable = pos.mutableCopy();
		for(int i = 1; i < range; i++) {
			BlockPos currPos = mutable.offset(direction, i);
			if(world.getBlockState(currPos) == searchedState) {
				return Optional.of(currPos);
			}
		}
		return Optional.empty();
	}
	
}
