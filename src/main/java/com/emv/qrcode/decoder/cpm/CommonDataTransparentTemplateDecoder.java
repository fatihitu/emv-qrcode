package com.emv.qrcode.decoder.cpm;

import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.BERTLBinary;
import com.emv.qrcode.model.cpm.CommonDataTransparentTemplate;

public final class CommonDataTransparentTemplateDecoder extends DecoderCpm<CommonDataTransparentTemplate> {

  private static final Entry<Class<?>, BiConsumer<CommonDataTransparentTemplate, ?>> defaultEntry = consumerTagLengthValue(BERTLBinary.class, CommonDataTransparentTemplate::addAdditionalData);

  public CommonDataTransparentTemplateDecoder(final byte[] source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CommonDataTransparentTemplate decode() {

    final CommonDataTransparentTemplate result = new CommonDataTransparentTemplate();

    while (iterator.hasNext()) {
      final byte[] value = iterator.next();

      final Class<?> clazz = defaultEntry.getKey();

      final BiConsumer consumer = defaultEntry.getValue();

      consumer.accept(result, DecoderCpm.decode(value, clazz));
    }

    return result;

  }

}