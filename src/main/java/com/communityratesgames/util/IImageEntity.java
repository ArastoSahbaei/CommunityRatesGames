package com.communityratesgames.util;

import com.communityratesgames.util.FileLimitReachedException;
import com.communityratesgames.util.InvalidFileFormatException;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;

public interface IImageEntity {
    public void storeImage(InputStream data) throws IOException, FileLimitReachedException, InvalidFileFormatException;
    public File loadImage() throws IOException;
}
