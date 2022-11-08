package gripe._90.megacells.init.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

import appeng.core.definitions.BlockDefinition;

import gripe._90.megacells.definition.MEGABlocks;

@Environment(EnvType.CLIENT)
public class InitRenderTypes {
    private static final BlockDefinition<?>[] CUTOUT_BLOCKS = {
            MEGABlocks.MEGA_CRAFTING_UNIT,
            MEGABlocks.CRAFTING_ACCELERATOR,
            MEGABlocks.CRAFTING_STORAGE_1M,
            MEGABlocks.CRAFTING_STORAGE_4M,
            MEGABlocks.CRAFTING_STORAGE_16M,
            MEGABlocks.CRAFTING_STORAGE_64M,
            MEGABlocks.CRAFTING_STORAGE_256M,
            MEGABlocks.CRAFTING_MONITOR
    };

    public static void init() {
        for (var definition : CUTOUT_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(definition.block(), RenderType.cutout());
        }
    }
}
