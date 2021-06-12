package com.gssg.gssgbe.common.convert;

import com.gssg.gssgbe.common.exception.ErrorCode;
import com.gssg.gssgbe.common.exception.custom.CustomSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Converter
public class EncryptConverter implements AttributeConverter<String, String> {

  private static String encryptAlgorithm;
  private static Key key;

  @Value("${application.encryption.encryptionKey}")
  public void create(String encryptionKey) {
    encryptAlgorithm = "AES";
    key = new SecretKeySpec(encryptionKey.getBytes(), encryptAlgorithm);

    log.info("### EncryptConverter encryptionKey={}", encryptionKey);
  }

  @Override
  public String convertToDatabaseColumn(String entityAttribute) {
    if (entityAttribute == null) {
      return null;
    }
    if (entityAttribute.isEmpty()) {
      return "";
    }

    try {
      Cipher cipher = Cipher.getInstance(encryptAlgorithm);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return new String(Hex.encodeHex(cipher.doFinal(entityAttribute.getBytes()))).toUpperCase();
    } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
      throw new CustomSecurityException(ErrorCode.FAIL_ENCRYPT);
    }
  }

  @Override
  public String convertToEntityAttribute(String databaseColumn) {
    if (databaseColumn == null) {
      return null;
    }
    if (databaseColumn.isEmpty()) {
      return "";
    }

    try {
      Cipher cipher = Cipher.getInstance(encryptAlgorithm);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(Hex.decodeHex(databaseColumn)));
    } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | DecoderException ex) {
      throw new CustomSecurityException(ErrorCode.FAIL_DECRYPT);
    }
  }
}
