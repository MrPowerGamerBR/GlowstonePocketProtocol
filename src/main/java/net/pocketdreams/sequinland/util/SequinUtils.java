package net.pocketdreams.sequinland.util;

import net.marfgamer.jraknet.protocol.Reliability;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.PocketNetworkManager;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.util.nukkit.Binary;
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

    public static void batchPackets(RakNetClientSession session, GamePacket[] packets) {
        byte[][] payload = new byte[packets.length * 2][];
        for (int i = 0; i < packets.length; i++) {
            GamePacket p = packets[i];
            byte[] buf = p.array();
            payload[i * 2] = Binary.writeUnsignedVarInt(buf.length);
            payload[i * 2 + 1] = buf;
        }
        byte[] data;
        data = Binary.appendBytes(payload);

        try {
            // TODO: Customizable compression
            byte[] packetPayload = Zlib.deflate(data, 7);
            BatchPacket pk = new BatchPacket();
            pk.payload = packetPayload;
            pk.encode();
            session.sendMessage(Reliability.RELIABLE, pk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
