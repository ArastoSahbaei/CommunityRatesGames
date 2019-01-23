package com.communityratesgames.util;

import com.communityratesgames.util.InvalidFileFormatException;

public class ImageUtils {
    public enum Format {
        IMAGE_PNG,
        IMAGE_JPEG,
        IMAGE_TIFF
    }

    public static final byte[] pngMagic = new byte[] {
        (byte)0x89, (byte)0x50, (byte)0x4e, (byte)0x47
    };
    public static final byte[] jpegMagic = new byte[] {
        (byte)0xff, (byte)0xd8, (byte)0xff
    };
    public static final byte[] tiffBigMagic = new byte[] {
        (byte)0x4d, (byte)0x4d, (byte)0x00, (byte)0x2a
    };
    public static final byte[] tiffLilMagic = new byte[] {
        (byte)0x49, (byte)0x49, (byte)0x2a, (byte)0x00
    };

    private static boolean compareN(byte[] b1, byte[] b2, int len) {
        if (b1.length < len || b2.length < len) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (b1[i] != b2[i]) {
                return false;
            }
        }
        
        return true;
    }

    public static Format getFormat(byte[] buffer) throws InvalidFileFormatException {
        // All images have a so-called "magic number", which is a number in the
        // beginning of the file to determine the file format. This is more
        // reliable than checking the file extension, as then we can gurantee
        // that the file format is actually correct.
        if (compareN(buffer, pngMagic, 4)) {
            return Format.IMAGE_PNG;
        }

        if (compareN(buffer, jpegMagic, 3)) {
            return Format.IMAGE_JPEG;
        }

        if (compareN(buffer, tiffBigMagic, 4) || compareN(buffer, tiffLilMagic, 4)) {
            return Format.IMAGE_TIFF;
        }

        throw new InvalidFileFormatException("invalid file format");
    }
}
