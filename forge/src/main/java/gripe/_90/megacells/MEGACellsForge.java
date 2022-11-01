package gripe._90.megacells;

import net.minecraft.core.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import gripe._90.megacells.block.MEGABlocks;
import gripe._90.megacells.block.entity.MEGABlockEntities;
import gripe._90.megacells.datagen.MEGADataGenerators;
import gripe._90.megacells.init.InitStorageCells;
import gripe._90.megacells.init.InitUpgrades;
import gripe._90.megacells.init.Registration;
import gripe._90.megacells.init.client.InitAutoRotatingModel;
import gripe._90.megacells.init.client.InitBlockEntityRenderers;
import gripe._90.megacells.init.client.InitBuiltInModels;
import gripe._90.megacells.init.client.InitItemColors;
import gripe._90.megacells.init.client.InitRenderTypes;
import gripe._90.megacells.integration.ae2wt.AE2WTIntegrationForge;
import gripe._90.megacells.integration.appmek.AppMekIntegration;
import gripe._90.megacells.integration.appmek.item.AppMekItems;
import gripe._90.megacells.item.MEGAItems;

@Mod(MEGACells.MODID)
public class MEGACellsForge {
    public MEGACellsForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MEGABlocks.init();
        MEGAItems.init();
        MEGABlockEntities.init();

        AppMekItems.init();

        bus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registry.BLOCK_REGISTRY)) {
                Registration.registerBlocks(ForgeRegistries.BLOCKS);
            }
            if (event.getRegistryKey().equals(Registry.ITEM_REGISTRY)) {
                Registration.registerItems(ForgeRegistries.ITEMS);
            }
            if (event.getRegistryKey().equals(Registry.BLOCK_ENTITY_TYPE_REGISTRY)) {
                Registration.registerBlockEntities(ForgeRegistries.BLOCK_ENTITY_TYPES);
            }
        });

        bus.addListener(MEGADataGenerators::onGatherData);
        bus.addListener((FMLCommonSetupEvent event) -> {
            event.enqueueWork(InitStorageCells::init);
            event.enqueueWork(InitUpgrades::init);

            event.enqueueWork(AE2WTIntegrationForge::initIntegration);
            event.enqueueWork(AppMekIntegration::initIntegration);
        });

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InitAutoRotatingModel::init);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InitBlockEntityRenderers::init);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InitBuiltInModels::init);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InitItemColors::init);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InitRenderTypes::init);
    }
}