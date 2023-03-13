package net.satisfy.candlelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.satisfy.candlelight.client.gui.CookingPanGui;
import net.satisfy.candlelight.client.gui.LetterGui;
import net.satisfy.candlelight.client.gui.WineStationGui;
import net.satisfy.candlelight.client.model.CookingHatModel;
import net.satisfy.candlelight.networking.CandlelightMessages;
import net.satisfy.candlelight.registry.ModBlockEntityTypes;
import net.satisfy.candlelight.registry.ObjectRegistry;
import net.satisfy.candlelight.registry.ScreenHandlerTypes;
import net.satisfy.candlelight.render.WineStationBlockEntityRenderer;


public class CandlelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ObjectRegistry.CAKE_STAND, ObjectRegistry.CHAIR, ObjectRegistry.TABLE);

        registerModels();
        CandlelightMessages.registerS2CPackets();

        HandledScreens.register(ScreenHandlerTypes.COOKING_PAN_SCREEN_HANDLER, CookingPanGui::new);
        HandledScreens.register(ScreenHandlerTypes.LETTER_SCREEN_HANDLER, LetterGui::new);
        HandledScreens.register(ScreenHandlerTypes.WINE_STATION_SCREEN_HANDLER, WineStationGui::new);


        BlockEntityRendererRegistry.register(ModBlockEntityTypes.WINE_STATION_BLOCK_ENTITY, WineStationBlockEntityRenderer::new);
    }



    private static void registerModels(){
        EntityModelLayerRegistry.registerModelLayer(CookingHatModel.LAYER_LOCATION, CookingHatModel::getTexturedModelData);
    }
}
