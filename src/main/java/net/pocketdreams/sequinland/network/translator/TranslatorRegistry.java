package net.pocketdreams.sequinland.network.translator;

import com.flowpowered.network.Message;
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
import net.marfgamer.jraknet.RakNetPacket;
import net.pocketdreams.sequinland.network.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.network.protocol.packets.LoginPacket;
import net.pocketdreams.sequinland.network.protocol.packets.MovePlayerPacket;
import net.pocketdreams.sequinland.network.protocol.packets.PlayerActionPacket;
import net.pocketdreams.sequinland.network.protocol.packets.RequestChunkRadiusPacket;
import net.pocketdreams.sequinland.network.protocol.packets.TextPacket;
import net.pocketdreams.sequinland.network.translator.both.IgnorePacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PEBatchPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PELoginPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PEMovePlayerPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PEPlayerActionPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PERequestChunkRadiusPacketTranslator;
import net.pocketdreams.sequinland.network.translator.pocket.PETextPacketTranslator;

import java.util.HashMap;

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
        // =======[ NOTCHIAN TO POCKET ]=======
        // Handshake
        PC_TO_PE_TRANSLATORS.put(HandshakeMessage.class, new IgnorePacketTranslator());

        // Login
        PC_TO_PE_TRANSLATORS.put(EncryptionKeyRequestMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(EncryptionKeyResponseMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(LoginStartMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(LoginSuccessMessage.class, new IgnorePacketTranslator());

        // Play
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
        PC_TO_PE_TRANSLATORS.put(VehicleMoveMessage.class, new IgnorePacketTranslator());

        // Game
        PC_TO_PE_TRANSLATORS.put(BlockActionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(BlockChangeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ChatMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ChunkDataMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ClientSettingsMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ExperienceMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(ExplosionMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(HealthMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(IncomingChatMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(JoinGameMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(MapDataMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(MultiBlockChangeMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(NamedSoundEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PingMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PlayEffectMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PlayParticleMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(PluginMessage.class, new IgnorePacketTranslator());
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
        PC_TO_PE_TRANSLATORS.put(UpdateSignMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UserListHeaderFooterMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(UserListItemMessage.class, new IgnorePacketTranslator());
        PC_TO_PE_TRANSLATORS.put(WorldBorderMessage.class, new IgnorePacketTranslator());

        // TODO: The rest of the packets
        // =======[ POCKET TO NOTCHIAN ]=======
        PE_TO_PC_TRANSLATORS.put(LoginPacket.class, new PELoginPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(BatchPacket.class, new PEBatchPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(TextPacket.class, new PETextPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(RequestChunkRadiusPacket.class, new PERequestChunkRadiusPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(MovePlayerPacket.class, new PEMovePlayerPacketTranslator());
        PE_TO_PC_TRANSLATORS.put(PlayerActionPacket.class, new PEPlayerActionPacketTranslator());
    }
}