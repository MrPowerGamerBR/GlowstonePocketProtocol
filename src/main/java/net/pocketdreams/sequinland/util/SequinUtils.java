package net.pocketdreams.sequinland.util;

import java.lang.reflect.InvocationTargetException;

import net.marfgamer.jraknet.Packet;
import net.marfgamer.jraknet.RakNetPacket;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.PocketNetworkManager;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.util.nukkit.BinaryStream;
import net.pocketdreams.sequinland.util.nukkit.Zlib;

public class SequinUtils {
    public static void processBatch(BatchPacket packet, RakNetClientSession session) {
        byte[] payload = null;
        try {
            payload = Zlib.inflate(packet.payload, 64 * 1024 * 1024);
        } catch (Exception e) {
            return;
        }

        BinaryStream stream = new BinaryStream(payload);
        while (payload.length > stream.offset) {
            byte[] packetPayload = stream.getByteArray();
            BinaryStream packetStream = new BinaryStream(packetPayload);
            byte id = (byte) packetStream.getByte();
            System.out.println("ID: " + id);
            byte[] packetBuffer = packetStream.getBuffer();
            
            Class<? extends GamePacket> clazz = ProtocolInfo.getPacketById(id);
            
            System.out.println("Packet ID: " + id);
            
            if (clazz != null) {
                System.out.println("Recieved packet: " + clazz.getSimpleName());
                try {
                    GamePacket pocketPacket = clazz.newInstance();
                    pocketPacket.setBuffer(packetBuffer);
                    PocketNetworkManager.handleRakNetPacket(session, pocketPacket);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
