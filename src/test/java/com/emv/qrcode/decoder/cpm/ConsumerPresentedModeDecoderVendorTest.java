package com.emv.qrcode.decoder.cpm;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.*;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// @formatter:off
public class ConsumerPresentedModeDecoderVendorTest {

  @Test
  public void testSuccessVendorDecode() throws Exception {
    final String encoded = "hQVDUFYwMWEYTwegAAAAd3d3UA1Nb2JpbGUgV2FsbGV0Yl5fLQZhcmVuZGVkU995BpcVgSNFZ994AiUC33cRQWJkdWxrYXJpbSBNb3VtaW7fdg1XYWxsZXQgSXNzdWVy33UIEjRWeJq83vDfdAEB33MEMTIzNN9yCDEyMzQ1Njc4";

    final ConsumerPresentedMode consumerPresentedMode = DecoderCpm.decode(encoded, ConsumerPresentedMode.class);

    final PayloadFormatIndicator payloadFormatIndicator = consumerPresentedMode.getPayloadFormatIndicator();
    assertThat(payloadFormatIndicator.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR));
    assertThat(payloadFormatIndicator.getStringValue(), equalTo("CPV01"));

    final ApplicationTemplate applicationTemplate1 = consumerPresentedMode.getApplicationTemplates().get(0);
    assertThat(applicationTemplate1.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_TEMPLATE));
    assertThat(applicationTemplate1.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(applicationTemplate1.getApplicationDefinitionFileName().getStringValue(), equalTo("A0000000777777"));
    assertThat(applicationTemplate1.getApplicationLabel().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_LABEL));
    assertThat(applicationTemplate1.getApplicationLabel().getStringValue(), equalTo("Mobile Wallet"));

    final CommonDataTemplate commonDataTemplate = consumerPresentedMode.getCommonDataTemplate();
    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(commonDataTemplate.getLanguagePreference().getTag(), equalTo(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE));
    assertThat(commonDataTemplate.getLanguagePreference().getStringValue(), equalTo("arende"));

    final CommonDataTransparentTemplate commonDataTransparentTemplate = commonDataTemplate.getCommonDataTransparentTemplate();
    assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(commonDataTransparentTemplate.getVendorMobileNumber().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_MOBILE_NUMBER));
    assertThat(commonDataTransparentTemplate.getVendorMobileNumber().getStringValue(), equalTo("971581234567"));
    assertThat(commonDataTransparentTemplate.getVendorExpiryDate().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_EXPIRY_DATE));
    assertThat(commonDataTransparentTemplate.getVendorExpiryDate().getStringValue(), equalTo("2502"));
    assertThat(commonDataTransparentTemplate.getVendorName().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_NAME));
    assertThat(commonDataTransparentTemplate.getVendorName().getStringValue(), equalTo("Abdulkarim Moumin"));
    assertThat(commonDataTransparentTemplate.getVendorWalletIssuer().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_WALLET_ISSUER));
    assertThat(commonDataTransparentTemplate.getVendorWalletIssuer().getStringValue(), equalTo("Wallet Issuer"));
    assertThat(commonDataTransparentTemplate.getVendorCryptogram().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_CRYPTOGRAM));
    assertThat(commonDataTransparentTemplate.getVendorCryptogram().getStringValue(), equalTo("123456789ABCDEF0"));
    assertThat(commonDataTransparentTemplate.getVendorOfflineOnlineResult().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_OFFLINE_ONLINE_RESULT));
    assertThat(commonDataTransparentTemplate.getVendorOfflineOnlineResult().getStringValue(), equalTo("01"));
    assertThat(commonDataTransparentTemplate.getVendorPin().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_PIN));
    assertThat(commonDataTransparentTemplate.getVendorPin().getStringValue(), equalTo("1234"));
    assertThat(commonDataTransparentTemplate.getVendorTokenRequestorId().getTag(), equalTo(TagTransactionProcessingCodes.ID_VENDOR_TOKEN_REQUESTOR_ID));
    assertThat(commonDataTransparentTemplate.getVendorTokenRequestorId().getStringValue(), equalTo("12345678"));

  }

}
// @formatter:on
