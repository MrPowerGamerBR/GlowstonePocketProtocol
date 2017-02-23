package net.pocketdreams.sequinland.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.marfgamer.jraknet.Packet;
import net.marfgamer.jraknet.RakNetPacket;
import net.marfgamer.jraknet.session.RakNetClientSession;
import net.pocketdreams.sequinland.net.PocketNetworkManager;
import net.pocketdreams.sequinland.net.PocketSession;
import net.pocketdreams.sequinland.net.protocol.ProtocolInfo;
import net.pocketdreams.sequinland.net.protocol.packets.BatchPacket;
import net.pocketdreams.sequinland.net.protocol.packets.GamePacket;
import net.pocketdreams.sequinland.util.nukkit.BinaryStream;
import net.pocketdreams.sequinland.util.nukkit.Zlib;

public class SequinUtils {
    public static void processBatch(BatchPacket packet, RakNetClientSession session) {
        byte[] data;
        try {
            data = Zlib.inflate(packet.payload, 64 * 1024 * 1024);
        } catch (Exception e) {
            return;
        }

        int idx = 0;
        while (data.length > idx) {
            
        }
            
        int len = data.length;
        BinaryStream stream = new BinaryStream(data);
        List<GamePacket> packets = new ArrayList<>();
        while (stream.offset < len) {
            byte[] buf = stream.getByteArray();

            RakNetPacket pk = new RakNetPacket(buf);
            
            PocketNetworkManager.handleRakNetPacket(session, pk);
        }
    }
}
