package net.glowstone.net.protocol;

import net.pocketdreams.sequinland.glowstone.net.protocol.PocketProtocol;

/**
 * Enumeration of the different Minecraft protocol states.
 */
public enum ProtocolType {
    HANDSHAKE(new HandshakeProtocol()),
    STATUS(new StatusProtocol()),
    LOGIN(new LoginProtocol()),
    PLAY(new PlayProtocol()),
	POCKET_EDITION(new PocketProtocol());

    private final GlowProtocol protocol;

    ProtocolType(GlowProtocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Get a GlowProtocol corresponding to this protocol type.
     *
     * @return A matching GlowProtocol.
     */
    public GlowProtocol getProtocol() {
        return protocol;
    }
}
