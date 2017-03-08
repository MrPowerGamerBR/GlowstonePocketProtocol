package net.pocketdreams.sequinland.glowstone.net.codec;

import java.io.IOException;

import com.flowpowered.network.Codec;

import io.netty.buffer.ByteBuf;
import net.pocketdreams.sequinland.glowstone.net.message.LoginPacket;

public class LoginPacketCodec implements Codec<LoginPacket> {

	@Override
	public LoginPacket decode(ByteBuf arg0) throws IOException {
		System.out.println(getClass().getCanonicalName() + ".decode()");
		return null;
	}

	@Override
	public ByteBuf encode(ByteBuf arg0, LoginPacket arg1) throws IOException {
		System.out.println(getClass().getCanonicalName() + ".encode()");
		return null;
	}

}
