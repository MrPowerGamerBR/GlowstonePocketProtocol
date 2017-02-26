package net.pocketdreams.sequinland.network.protocol;

import java.util.HashMap;

import net.pocketdreams.sequinland.network.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.network.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.network.protocol.packets.LoginPacket;
import net.pocketdreams.sequinland.network.protocol.packets.MovePlayerPacket;
import net.pocketdreams.sequinland.network.protocol.packets.PlayerActionPacket;
import net.pocketdreams.sequinland.network.protocol.packets.RequestChunkRadiusPacket;
import net.pocketdreams.sequinland.network.protocol.packets.TextPacket;

public class ProtocolInfo {
    public static HashMap<Short, Class<? extends GamePacket>> packets = new HashMap<>(); 
    public static final int CURRENT_PROTOCOL = 101;
    public static final String MINECRAFT_VERSION_NETWORK = "1.0.3.0";
    
    public static final short LOGIN_PACKET = 0x01;
    public static final short PLAY_STATUS_PACKET = 0x02;
    public static final short SERVER_TO_CLIENT_HANDSHAKE_PACKET = 0x03;
    public static final short CLIENT_TO_SERVER_HANDSHAKE_PACKET = 0x04;
    public static final short DISCONNECT_PACKET = 0x05;
    public static final short BATCH_PACKET = 0x06;
    public static final short RESOURCE_PACKS_INFO_PACKET = 0x07;
    public static final short RESOURCE_PACK_STACK_PACKET = 0x08;
    public static final short RESOURCE_PACK_CLIENT_RESPONSE_PACKET = 0x09;
    public static final short TEXT_PACKET = 0x0a;
    public static final short SET_TIME_PACKET = 0x0b;
    public static final short START_GAME_PACKET = 0x0c;
    public static final short ADD_PLAYER_PACKET = 0x0d;
    public static final short ADD_ENTITY_PACKET = 0x0e;
    public static final short REMOVE_ENTITY_PACKET = 0x0f;
    public static final short ADD_ITEM_ENTITY_PACKET = 0x10;
    public static final short ADD_HANGING_ENTITY_PACKET = 0x11;
    public static final short TAKE_ITEM_ENTITY_PACKET = 0x12;
    public static final short MOVE_ENTITY_PACKET = 0x13;
    public static final short MOVE_PLAYER_PACKET = 0x14;
    public static final short RIDER_JUMP_PACKET = 0x15;
    public static final short REMOVE_BLOCK_PACKET = 0x16;
    public static final short UPDATE_BLOCK_PACKET = 0x17;
    public static final short ADD_PAINTING_PACKET = 0x18;
    public static final short EXPLODE_PACKET = 0x19;
    public static final short LEVEL_SOUND_EVENT_PACKET = 0x1a;
    public static final short LEVEL_EVENT_PACKET = 0x1b;
    public static final short BLOCK_EVENT_PACKET = 0x1c;
    public static final short ENTITY_EVENT_PACKET = 0x1d;
    public static final short MOB_EFFECT_PACKET = 0x1e;
    public static final short UPDATE_ATTRIBUTES_PACKET = 0x1f;
    public static final short MOB_EQUIPMENT_PACKET = 0x20;
    public static final short MOB_ARMOR_EQUIPMENT_PACKET = 0x21;
    public static final short INTERACT_PACKET = 0x22;
    public static final short USE_ITEM_PACKET = 0x23;
    public static final short PLAYER_ACTION_PACKET = 0x24;
    public static final short PLAYER_FALL_PACKET = 0x25;
    public static final short HURT_ARMOR_PACKET = 0x26;
    public static final short SET_ENTITY_DATA_PACKET = 0x27;
    public static final short SET_ENTITY_MOTION_PACKET = 0x28;
    public static final short SET_ENTITY_LINK_PACKET = 0x29;
    public static final short SET_HEALTH_PACKET = 0x2a;
    public static final short SET_SPAWN_POSITION_PACKET = 0x2b;
    public static final short ANIMATE_PACKET = 0x2c;
    public static final short RESPAWN_PACKET = 0x2d;
    public static final short DROP_ITEM_PACKET = 0x2e;
    public static final short INVENTORY_ACTION_PACKET = 0x2f;
    public static final short CONTAINER_OPEN_PACKET = 0x30;
    public static final short CONTAINER_CLOSE_PACKET = 0x31;
    public static final short CONTAINER_SET_SLOT_PACKET = 0x32;
    public static final short CONTAINER_SET_DATA_PACKET = 0x33;
    public static final short CONTAINER_SET_CONTENT_PACKET = 0x34;
    public static final short CRAFTING_DATA_PACKET = 0x35;
    public static final short CRAFTING_EVENT_PACKET = 0x36;
    public static final short ADVENTURE_SETTINGS_PACKET = 0x37;
    public static final short BLOCK_ENTITY_DATA_PACKET = 0x38;
    public static final short PLAYER_INPUT_PACKET = 0x39;
    public static final short FULL_CHUNK_DATA_PACKET = 0x3a;
    public static final short SET_COMMANDS_ENABLED_PACKET = 0x3b;
    public static final short SET_DIFFICULTY_PACKET = 0x3c;
    public static final short CHANGE_DIMENSION_PACKET = 0x3d;
    public static final short SET_PLAYER_GAME_TYPE_PACKET = 0x3e;
    public static final short PLAYER_LIST_PACKET = 0x3f;
    public static final short EVENT_PACKET = 0x40;
    public static final short SPAWN_EXPERIENCE_ORB_PACKET = 0x41;
    public static final short CLIENTBOUND_MAP_ITEM_DATA_PACKET = 0x42;
    public static final short MAP_INFO_REQUEST_PACKET = 0x43;
    public static final short REQUEST_CHUNK_RADIUS_PACKET = 0x44;
    public static final short CHUNK_RADIUS_UPDATED_PACKET = 0x45;
    public static final short ITEM_FRAME_DROP_ITEM_PACKET = 0x46;
    public static final short REPLACE_ITEM_IN_SLOT_PACKET = 0x47; //ReplaceSelectedItemPacket
    public static final short GAME_RULES_CHANGED_PACKET = 0x48;
    public static final short CAMERA_PACKET = 0x49;
    public static final short ADD_ITEM_PACKET = 0x4a;
    public static final short BOSS_EVENT_PACKET = 0x4b;
    public static final short AVAILABLE_COMMANDS_PACKET = 0x4d;
    public static final short COMMAND_STEP_PACKET = 0x4e;
    public static final short RESOURCE_PACK_DATA_INFO_PACKET = 0x4f;
    public static final short RESOURCE_PACK_CHUNK_DATA_PACKET = 0x50;
    public static final short RESOURCE_PACK_CHUNK_REQUEST_PACKET = 0x51;
    public static final short TRANSFER_PACKET = 0x52;
    
    static {
        packets.put(LOGIN_PACKET, LoginPacket.class);
        packets.put(TEXT_PACKET, TextPacket.class);
        packets.put(BATCH_PACKET, BatchPacket.class);
        packets.put(REQUEST_CHUNK_RADIUS_PACKET, RequestChunkRadiusPacket.class);
        packets.put(MOVE_PLAYER_PACKET, MovePlayerPacket.class);
        packets.put(PLAYER_ACTION_PACKET, PlayerActionPacket.class);
    }
    
    public static Class<? extends GamePacket> getPacketById(short id) {
        return packets.get(id);
    }
}
