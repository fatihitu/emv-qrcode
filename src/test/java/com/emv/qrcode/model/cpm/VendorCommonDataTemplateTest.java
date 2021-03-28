package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// @formatter:off
public class VendorCommonDataTemplateTest {

  @Test
  public void testVendorSuccessToHexUseCase() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName("A0000000777777");
    commonDataTemplate.setApplicationLabel("Mobile Wallet");
    commonDataTemplate.setVendorMobileNumber("971581234567");
    commonDataTemplate.setVendorExpiryDate("2502");
    commonDataTemplate.setVendorName("Abdulkarim Moumin");
    commonDataTemplate.setVendorWalletIssuer("Wallet Issuer");
    commonDataTemplate.setVendorCryptogram("123456789ABCDEF0");
    commonDataTemplate.setVendorOfflineOnlineResult("01");
    commonDataTemplate.setVendorPin("1234");
    commonDataTemplate.setVendorTokenRequestorId("12345678");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(
            "626B4F07A0000000777777500D4D6F62696C652057616C6C6574" +
                    "DF7906971581234567" +
                    "DF78022502" +
                    "DF7711416264756C6B6172696D204D6F756D696E" +
                    "DF760D57616C6C657420497373756572" +
                    "DF7508123456789ABCDEF0" +
                    "DF740101" +
                    "DF730431323334" +
                    "DF72083132333435363738"));
  }
  @Test
  public void testVendorSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
    commonDataTransparentTemplate.addAdditionalData(new BERTLAlphanumeric(new byte[] { 0x00 }, "7654"));

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName("7654");
    commonDataTemplate.setApplicationLabel("7654");
    commonDataTemplate.setVendorMobileNumber("7654");
    commonDataTemplate.setVendorExpiryDate("7654");
    commonDataTemplate.setVendorName("7654");
    commonDataTemplate.setVendorWalletIssuer("7654");
    commonDataTemplate.setVendorCryptogram("7654");
    commonDataTemplate.setVendorOfflineOnlineResult("7654");
    commonDataTemplate.setVendorPin("7654");
    commonDataTemplate.setVendorTokenRequestorId("7654");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(
        "62424F027654500437363534DF79027654DF78027654DF770437363534DF760437363534DF75027654DF74027654DF730437363534DF7204373635346406000437363534"));
  }

  @Test
  public void testVendorSuccessToHexWhenValueIsEmpty() throws IOException {

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName(StringUtils.EMPTY);
    commonDataTemplate.setApplicationLabel(StringUtils.EMPTY);
    commonDataTemplate.setVendorMobileNumber(StringUtils.EMPTY);
    commonDataTemplate.setVendorExpiryDate(StringUtils.EMPTY);
    commonDataTemplate.setVendorName(StringUtils.EMPTY);
    commonDataTemplate.setVendorWalletIssuer(StringUtils.EMPTY);
    commonDataTemplate.setVendorCryptogram(StringUtils.EMPTY);
    commonDataTemplate.setVendorOfflineOnlineResult(StringUtils.EMPTY);
    commonDataTemplate.setVendorPin(StringUtils.EMPTY);
    commonDataTemplate.setVendorTokenRequestorId(StringUtils.EMPTY);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testVendorSuccessToHexWhenValueIsNull() throws IOException {

    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();

    commonDataTemplate.setApplicationDefinitionFileName(null);
    commonDataTemplate.setApplicationLabel(null);
    commonDataTemplate.setVendorMobileNumber(null);
    commonDataTemplate.setVendorExpiryDate(null);
    commonDataTemplate.setVendorName(null);
    commonDataTemplate.setVendorWalletIssuer(null);
    commonDataTemplate.setVendorCryptogram(null);
    commonDataTemplate.setVendorOfflineOnlineResult(null);
    commonDataTemplate.setVendorPin(null);
    commonDataTemplate.setVendorTokenRequestorId(null);

    assertThat(commonDataTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTemplate.getBytes(), false), equalTo(StringUtils.EMPTY));

  }

}
// @formatter:on
