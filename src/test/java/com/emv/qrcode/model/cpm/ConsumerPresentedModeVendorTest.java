package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.cpm.BERTemplate;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// @formatter:off
public class ConsumerPresentedModeVendorTest {


  @Test
  public void testSuccessToHexWithApplicationTemplate() throws IOException {
    final ConsumerPresentedMode consumerPresentedMode = new ConsumerPresentedMode();

    // 61 Application Level
    final ApplicationTemplate applicationTemplate1 = new ApplicationTemplate();
    applicationTemplate1.setApplicationDefinitionFileName("A0000000777777");
    applicationTemplate1.setApplicationLabel("Mobile Wallet");

    // 64 Specific data
    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();
    commonDataTransparentTemplate.setVendorMobileNumber("971581234567");
    commonDataTransparentTemplate.setVendorExpiryDate("2502");
    commonDataTransparentTemplate.setVendorName("Abdulkarim Moumin");
    commonDataTransparentTemplate.setVendorWalletIssuer("Wallet Issuer");
    commonDataTransparentTemplate.setVendorCryptogram("123456789ABCDEF0");
    commonDataTransparentTemplate.setVendorOfflineOnlineResult("01");
    commonDataTransparentTemplate.setVendorPin("1234");

    // 62 Common Data
    final CommonDataTemplate commonDataTemplate = new CommonDataTemplate();
    commonDataTemplate.setLanguagePreference("arende");
    commonDataTemplate.setTokenRequestorID("12345678");
    commonDataTemplate.setCommonDataTransparentTemplate(commonDataTransparentTemplate);

    consumerPresentedMode.setPayloadFormatIndicator(new PayloadFormatIndicator());
    consumerPresentedMode.addApplicationTemplate(applicationTemplate1);
    consumerPresentedMode.setCommonDataTemplate(commonDataTemplate);

    assertThat(consumerPresentedMode.toHex(),
            equalTo("850543505630316118" +
                    "4F07A0000000777777" +
                    "500D4D6F62696C652057616C6C6574" +
                    "625A5F2D066172656E64659F190412345678" +
                    "6448" +
                    "DF7906971581234567DF78022502DF7711416264756C6B6172696D204D6F756D696EDF760D57616C6C657420497373756572DF7508123456789ABCDEF0DF740101DF730431323334"));

    assertThat(consumerPresentedMode.toBase64(), equalTo(
          "hQVDUFYwMWEYTwegAAAAd3d3UA1Nb2JpbGUgV2FsbGV0YlpfLQZhcmVuZGWfGQQSNFZ4ZEjfeQaXFYEjRWffeAIlAt93EUFiZHVsa2FyaW0gTW91bWlu33YNV2FsbGV0IElzc3Vlct91CBI0VniavN7w33QBAd9zBDEyMzQ="));
  }


}
// @formatter:on
