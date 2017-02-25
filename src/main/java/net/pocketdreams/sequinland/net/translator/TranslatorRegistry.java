package net.pocketdreams.sequinland.net.translator;

import com.flowpowered.network.Message;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.glowstone.net.message.KickMessage;
import net.glowstone.net.message.SetCompressionMessage;
import net.glowstone.net.message.handshake.HandshakeMessage;
import net.glowstone.net.message.login.EncryptionKeyRequestMessage;
import net.glowstone.net.message.login.EncryptionKeyResponseMessage;
import net.glowstone.net.message.login.LoginStartMessage;
import net.glowstone.net.message.login.LoginSuccessMessage;
import net.glowstone.net.message.play.entity.AnimateEntityMessage;
import net.glowstone.net.message.play.entity.AttachEntityMessage;
import net.glowstone.net.message.play.entity.CollectItemMessage;
import net.glowstone.net.message.play.entity.DestroyEntitiesMessage;
import net.glowstone.net.message.play.entity.EntityEffectMessage;
import net.glowstone.net.message.play.entity.EntityEquipmentMessage;
import net.glowstone.net.message.play.entity.EntityHeadRotationMessage;
import net.glowstone.net.message.play.entity.EntityMetadataMessage;
import net.glowstone.net.message.play.entity.EntityPropertyMessage;
import net.glowstone.net.message.play.entity.EntityRemoveEffectMessage;
import net.glowstone.net.message.play.entity.EntityRotationMessage;
import net.glowstone.net.message.play.entity.EntityStatusMessage;
import net.glowstone.net.message.play.entity.EntityTeleportMessage;
import net.glowstone.net.message.play.entity.EntityVelocityMessage;
import net.glowstone.net.message.play.entity.RelativeEntityPositionMessage;
import net.glowstone.net.message.play.entity.RelativeEntityPositionRotationMessage;
import net.glowstone.net.message.play.entity.SetCooldownMessage;
import net.glowstone.net.message.play.entity.SetPassengerMessage;
import net.glowstone.net.message.play.entity.SpawnLightningStrikeMessage;
import net.glowstone.net.message.play.entity.SpawnMobMessage;
import net.glowstone.net.message.play.entity.SpawnObjectMessage;
import net.glowstone.net.message.play.entity.SpawnPaintingMessage;
import net.glowstone.net.message.play.entity.SpawnPlayerMessage;
import net.glowstone.net.message.play.entity.SpawnXpOrbMessage;
import net.glowstone.net.message.play.entity.VehicleMoveMessage;
import net.glowstone.net.message.play.game.BlockActionMessage;
import net.glowstone.net.message.play.game.BlockChangeMessage;
import net.glowstone.net.message.play.game.ChatMessage;
import net.glowstone.net.message.play.game.ChunkDataMessage;
import net.glowstone.net.message.play.game.ClientSettingsMessage;
import net.glowstone.net.message.play.game.ExperienceMessage;
import net.glowstone.net.message.play.game.ExplosionMessage;
import net.glowstone.net.message.play.game.HealthMessage;
import net.glowstone.net.message.play.game.IncomingChatMessage;
import net.glowstone.net.message.play.game.JoinGameMessage;
import net.glowstone.net.message.play.game.MapDataMessage;
import net.glowstone.net.message.play.game.MultiBlockChangeMessage;
import net.glowstone.net.message.play.game.NamedSoundEffectMessage;
import net.glowstone.net.message.play.game.PingMessage;
import net.glowstone.net.message.play.game.PlayEffectMessage;
import net.glowstone.net.message.play.game.PlayParticleMessage;
import net.glowstone.net.message.play.game.PluginMessage;
import net.glowstone.net.message.play.game.PositionRotationMessage;
import net.glowstone.net.message.play.game.RespawnMessage;
import net.glowstone.net.message.play.game.SignEditorMessage;
import net.glowstone.net.message.play.game.SoundEffectMessage;
import net.glowstone.net.message.play.game.SpawnPositionMessage;
import net.glowstone.net.message.play.game.StateChangeMessage;
import net.glowstone.net.message.play.game.StatisticMessage;
import net.glowstone.net.message.play.game.TimeMessage;
import net.glowstone.net.message.play.game.TitleMessage;
import net.glowstone.net.message.play.game.UnloadChunkMessage;
import net.glowstone.net.message.play.game.UpdateBlockEntityMessage;
import net.glowstone.net.message.play.game.UpdateSignMessage;
import net.glowstone.net.message.play.game.UserListHeaderFooterMessage;
import net.glowstone.net.message.play.game.UserListItemMessage;
import net.glowstone.net.message.play.game.WorldBorderMessage;
import net.glowstone.net.message.play.inv.CloseWindowMessage;
import net.glowstone.net.message.play.inv.CreativeItemMessage;
import net.glowstone.net.message.play.inv.EnchantItemMessage;
import net.glowstone.net.message.play.inv.HeldItemMessage;
import net.glowstone.net.message.play.inv.OpenWindowMessage;
import net.glowstone.net.message.play.inv.SetWindowContentsMessage;
import net.glowstone.net.message.play.inv.SetWindowSlotMessage;
import net.glowstone.net.message.play.inv.TransactionMessage;
import net.glowstone.net.message.play.inv.WindowClickMessage;
import net.glowstone.net.message.play.inv.WindowPropertyMessage;
import net.glowstone.net.message.play.player.BlockPlacementMessage;
import net.glowstone.net.message.play.player.BossBarMessage;
import net.glowstone.net.message.play.player.CameraMessage;
import net.glowstone.net.message.play.player.ClientStatusMessage;
import net.glowstone.net.message.play.player.CombatEventMessage;
import net.glowstone.net.message.play.player.DiggingMessage;
import net.glowstone.net.message.play.player.InteractEntityMessage;
import net.glowstone.net.message.play.player.PlayerAbilitiesMessage;
import net.glowstone.net.message.play.player.PlayerActionMessage;
import net.glowstone.net.message.play.player.PlayerLookMessage;
import net.glowstone.net.message.play.player.PlayerPositionLookMessage;
import net.glowstone.net.message.play.player.PlayerPositionMessage;
import net.glowstone.net.message.play.player.PlayerSwingArmMessage;
import net.glowstone.net.message.play.player.PlayerUpdateMessage;
import net.glowstone.net.message.play.player.ResourcePackSendMessage;
import net.glowstone.net.message.play.player.ResourcePackStatusMessage;
import net.glowstone.net.message.play.player.ServerDifficultyMessage;
import net.glowstone.net.message.play.player.SpectateMessage;
import net.glowstone.net.message.play.player.SteerVehicleMessage;
import net.glowstone.net.message.play.player.TabCompleteMessage;
import net.glowstone.net.message.play.player.TabCompleteResponseMessage;
import net.glowstone.net.message.play.player.TeleportConfirmMessage;
import net.glowstone.net.message.play.player.UseBedMessage;
import net.glowstone.net.message.play.scoreboard.ScoreboardDisplayMessage;
import net.glowstone.net.message.play.scoreboard.ScoreboardObjectiveMessage;
import net.glowstone.net.message.play.scoreboard.ScoreboardScoreMessage;
import net.glowstone.net.message.play.scoreboard.ScoreboardTeamMessage;
import net.glowstone.net.message.status.StatusPingMessage;
import net.glowstone.net.message.status.StatusRequestMessage;
import net.glowstone.net.message.status.StatusResponseMessage;
import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.LoginPacket;
import net.pocketdreams.sequinland.net.protocol.packets.MovePlayerPacket;
import net.pocketdreams.sequinland.net.protocol.packets.RequestChunkRadiusPacket;
import net.pocketdreams.sequinland.net.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.net.translator.both.IgnorePacketTranslator;
import net.pocketdreams.sequinland.net.translator.pocket.PEBatchPacketTranslator;
import net.pocketdreams.sequinland.net.translator.pocket.PELoginPacketTranslator;
import net.pocketdreams.sequinland.net.translator.pocket.PEMovePlayerPacketTranslator;
import net.pocketdreams.sequinland.net.translator.pocket.PERequestChunkRadiusPacketTranslator;
import net.pocketdreams.sequinland.net.translator.pocket.PETextPacketTranslator;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by daporkchop on 25.02.17.
 */
public final class TranslatorRegistry {

    /**
     * Maps PC packet classes to PC to PE packet translators
     */
    public static HashMap<Class<? extends Message>, PCToPETranslator> PC_TO_PE_TRANSLATORS = new HashMap<>();

    /**
     * Maps PE packet classes to PE to PC packet translators
     */
    public static HashMap<Class<? extends RakNetPacket>, PEToPCTranslator> PE_TO_PC_TRANSLATORS = new HashMap<>();

    static {
        // -----=====≡≡≡≡≡ BEGIN NOTCHIAN TO POCKET ≡≡≡≡≡=====-----
        //We only want to list outbound packets here

        // Login
        PC_TO_PE_TRANSLATORS.put(EncryptionKeyRequestMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(LoginSuccessMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(KickMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SetCompressionMessage.class, new IgnorePacketTranslator());

        // --== Begin play packets ==--

        // Entitiy
        PC_TO_PE_TRANSLATORS.put(AnimateEntityMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(AttachEntityMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(CollectItemMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(DestroyEntitiesMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityEquipmentMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityHeadRotationMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityMetadataMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityPropertyMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityRemoveEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityRotationMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityStatusMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityTeleportMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EntityVelocityMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(RelativeEntityPositionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(RelativeEntityPositionRotationMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SetCooldownMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SetPassengerMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnLightningStrikeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnMobMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnObjectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnPaintingMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnPlayerMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnXpOrbMessage.class, new IgnorePacketTranslator());

        // Game
        PC_TO_PE_TRANSLATORS.put(BlockActionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(BlockChangeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ChatMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ChunkDataMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ExperienceMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ExplosionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(HealthMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(JoinGameMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(MapDataMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(MultiBlockChangeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(NamedSoundEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PlayEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PlayParticleMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PositionRotationMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(RespawnMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SignEditorMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SoundEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SpawnPositionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(StateChangeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(StatisticMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(TimeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(TitleMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UnloadChunkMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UpdateBlockEntityMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UserListHeaderFooterMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UserListItemMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(WorldBorderMessage.class, new IgnorePacketTranslator());

        // Inventory
        PC_TO_PE_TRANSLATORS.put(OpenWindowMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SetWindowContentsMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(SetWindowSlotMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(WindowPropertyMessage.class, new IgnorePacketTranslator());

        // Player
        PC_TO_PE_TRANSLATORS.put(BossBarMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(CameraMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(CombatEventMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ResourcePackSendMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ServerDifficultyMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(TabCompleteResponseMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UseBedMessage.class, new IgnorePacketTranslator());

        // Scoreboard
        PC_TO_PE_TRANSLATORS.put(ScoreboardDisplayMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ScoreboardObjectiveMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ScoreboardScoreMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ScoreboardTeamMessage.class, new IgnorePacketTranslator());

        // --== End play packets ==--

        // Status
        PC_TO_PE_TRANSLATORS.put(StatusPingMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(StatusRequestMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(StatusResponseMessage.class, new IgnorePacketTranslator());

        // -----=====≡≡≡≡≡ END NOTCHIAN TO POCKET ≡≡≡≡≡=====-----

        // -----=====≡≡≡≡≡ BEGIN POCKET TO NOTCHIAN ≡≡≡≡≡=====-----
        PE_TO_PC_TRANSLATORS.put(BatchPacket.class, new PEBatchPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(LoginPacket.class, new PELoginPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(MovePlayerPacket.class, new PEMovePlayerPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(TextPacket.class, new PETextPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(RequestChunkRadiusPacket.class, new PERequestChunkRadiusPacketTranslator());

        // -----=====≡≡≡≡≡ END POCKET TO NOTCHIAN ≡≡≡≡≡=====-----

        // TODO: The rest of the packets
    }

    /**
     * Translates a PE packet into it's PC equivalent(s)
     * @param session
     * @param packet
     * @return
     */
    public static Message[] fromPE(PocketSession session, RakNetPacket packet) {
        PEToPCTranslator translator = PE_TO_PC_TRANSLATORS.get(packet.getClass());

        if (translator == null) {
            GlowServer.logger.log(Level.ALL, "Not translating unmapped PE packet to PC!!! This is an error!!!");
            return new Message[0];
        } else {
            return translator.translate(session, packet);
        }
    }

    /**
     * Translates a PC packet into it's PE equivalent(s)
     * @param session
     * @param packet
     * @return
     */
    public static RakNetPacket[] fromPC(GlowSession session, Message packet)    {
        PCToPETranslator translator = PC_TO_PE_TRANSLATORS.get(packet.getClass());

        if (translator == null) {
            GlowServer.logger.log(Level.ALL, "Not translating unmapped PC packet to PE!!! This is an error!!!");
            return new RakNetPacket[0];
        } else {
            return translator.translate(session, packet);
        }
    }
}