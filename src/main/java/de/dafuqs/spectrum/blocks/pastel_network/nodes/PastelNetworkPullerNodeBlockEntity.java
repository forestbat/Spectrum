package de.dafuqs.spectrum.blocks.pastel_network.nodes;

import de.dafuqs.spectrum.registries.SpectrumBlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class PastelNetworkPullerNodeBlockEntity extends PastelNetworkNodeBlockEntity {
	
	public PastelNetworkPullerNodeBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SpectrumBlockEntityRegistry.PULLER_NODE, blockPos, blockState);
	}
	
}