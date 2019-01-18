package com.communityratesgames.util;

import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface IImageEntity {
    public void storeImage(byte[] data) throws IOException, FileLimitReachedException, InvalidFileFormatException;
    public byte[] loadImage() throws IOException;
}
