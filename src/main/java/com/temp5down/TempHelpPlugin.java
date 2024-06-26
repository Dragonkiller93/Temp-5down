package com.temp5down;
import com.google.inject.Provides;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.api.Client;

@Slf4j
public class TempHelpPlugin extends Plugin {
    @Provides
    TempHelpConfig getConfig(ConfigManager configManager)
    {
        return configManager.getConfig(TempHelpConfig.class);
    }

    @Inject
    private Notifier notifier;

    @Inject
    private Client client;

    @Override
    protected void startUp() throws Exception {
    }

    @Override
    protected void shutDown() throws Exception
    {
    }
    private boolean started = true;
    private boolean isOutsideTemp()
    {
        if (client.getLocalPlayer() != null)
        {
            return (client.getLocalPlayer().getWorldLocation().getRegionID() == 12588 || client.getLocalPlayer().getWorldLocation().getRegionID() == 12332);
        }

        return false;
    }
    @Subscribe
    public void onGameTick(GameTick gameTick) {
        client.addChatMessage(ChatMessageType.GAMEMESSAGE,"Hi there","232",null);
        if (isOutsideTemp()) {

            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You're at tempoross!", null);
        }
        else if(started){
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "You're at region: " + client.getLocalPlayer().getWorldLocation().getRegionID(), null);
            started = false;
        }
    }
    @Subscribe
    public void onChatMessage(ChatMessage chatMessage) {
        client.addChatMessage(ChatMessageType.GAMEMESSAGE,"Hi there","232",null);
    }

}
