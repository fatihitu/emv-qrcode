package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.model.BERTLBinary;
import com.emv.qrcode.core.utils.BERUtils;

public final class BERTLBinaryDecoder extends DecoderCpm<BERTLBinary> {

  public BERTLBinaryDecoder(final byte[] source) {
    super(source);
  }

  @Override
  protected BERTLBinary decode() {
    final byte[] value = iterator.next();

    return new BERTLBinary(BERUtils.copyBytesOfTag(value), BERUtils.copyBytesOfLength(value));
  }

}