package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendorCommonDataTransparentTemplateTest {

  @Test
  public void testVendorSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    commonDataTransparentTemplate.setVendorMobileNumber("971581234567");
    commonDataTransparentTemplate.setVendorExpiryDate("2502");
    commonDataTransparentTemplate.setVendorName("Abdulkarim Moumin");
    commonDataTransparentTemplate.setVendorWalletIssuer("Wallet Issuer");
    commonDataTransparentTemplate.setVendorCryptogram("123456789ABCDEF0");
    commonDataTransparentTemplate.setVendorOfflineOnlineResult("01");
    commonDataTransparentTemplate.setVendorPin("1234");

    assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(Hex.encodeHexString(commonDataTransparentTemplate.getBytes(), false),
            equalTo("6448" +
                    "DF7906971581234567" +
                    "DF78022502" +
                    "DF7711416264756C6B6172696D204D6F756D696E" +
                    "DF760D57616C6C657420497373756572" +
                    "DF7508123456789ABCDEF0" +
                    "DF740101" +
                    "DF730431323334"));
  }


}
