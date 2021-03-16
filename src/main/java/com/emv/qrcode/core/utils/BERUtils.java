package com.emv.qrcode.core.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.emv.qrcode.core.model.BERTag;

// @formatter:off
public final class BERUtils {

  private static final int MAX_BYTE_VALUE = 0x80;

  private static final int NUMBER_OF_BYTES_MASK = 0x7F;

  private BERUtils() {
    super();
  }

  public static byte[] copyBytesOfTag(final byte[] source) {
    return Arrays.copyOfRange(source, 0, countBytesOfTag(source));
  }

  public static byte[] copyBytesOfLength(final byte[] source) {
    final int numberOfBytesTag = countBytesOfTag(source);
    final int numberOfBytesLength = countBytesOfLength(source, numberOfBytesTag);
    return Arrays.copyOfRange(source, numberOfBytesTag + numberOfBytesLength, valueOfLength(source));
  }

  public static Integer valueOfLength(final byte[] source) {

    final Integer start = countBytesOfTag(source);

    final Integer countBytesOfLength = countBytesOfLength(source, start);

    final byte[] array = ByteBuffer.allocate(2).array();

    /**
     * |No of Bytes |   Length   |           Coding           | Mask |
     * |------------+------------+----------------------------+------+
     * |     1      |    0-127   |          0xxxxxxx          | 0x7F |
     * |     2      |   128-255  |      10000001 xxxxxxxx     | 0x81 |
     * |     3      |  256-65535 | 10000010 xxxxxxxx xxxxxxxx | 0x82 |
     */
    for (int j = 0, i = start + countBytesOfLength - 1; i < start + countBytesOfLength; j++, i++) {
      array[j] = source[i];
    }

    return (int) ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public static byte[] lengthToBytes(final Integer value) {
    if (value > Byte.MAX_VALUE) {

      final Integer numberOfBytes = countBytes(value);

      if (numberOfBytes > 2) {
        throw new IllegalStateException("Encode the length is more then 2 bytes (65535)");
      }

      final byte[] bytes = new byte[numberOfBytes + 1];

      bytes[0] = (byte) (0x80 + numberOfBytes);

      final byte[] array = ByteBuffer.allocate(2).putShort(value.shortValue()).order(ByteOrder.LITTLE_ENDIAN).array();

      for (int i = 0; i < numberOfBytes; i++) {
        bytes[i + 1] = array[2 - numberOfBytes + i];
      }

      return bytes;
    }

    return new byte[] { value.byteValue() };
  }

  public static Integer countBytes(final Integer value) {
    if (value == 0) {
      return 0;
    } else {
      return countBytes(value >> 8) + 1;
    }
  }

  public static Integer countBytesOfTag(final byte[] source) {
    Integer count = 0;

    final boolean hasNextByte = BERTag.hasNextByte(source[count]);

    if (hasNextByte) {
      count++;
    }

    while (hasNextByte && BERTag.isNotLastByte(source[count])) {
      count++;
    }

    return count + 1;
  }

  public static Integer countBytesOfLength(final byte[] source, final Integer start) {
    if ((source[start] & MAX_BYTE_VALUE) == MAX_BYTE_VALUE) {
      final int numberOfBytes = (source[start] & NUMBER_OF_BYTES_MASK) + 1;

      if (numberOfBytes > 3) {
        throw new IllegalStateException("Decode the length is more then 2 bytes (65535)");
      }

      return numberOfBytes;
    }

    return 1;
  }

}

// @formatter:on