package cn.letcode.utils.security.xmlrsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class KryptoUtil {
	/**
	 * 获取pfx私钥
	 * 
	 * @param pfxPath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public PrivateKey getPrivateKey(String pfxPath, String pfxPasswd) {
		PrivateKey pk = null;
		KeyStore keyStore = null;
		try {
			FileInputStream fis = new FileInputStream(new File(pfxPath));
			keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(fis, pfxPasswd.toCharArray());
			// 获取我的证书链的中keyEntry的别名
			Enumeration enumas = keyStore.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements()) {
				keyAlias = (String) enumas.nextElement();
				System.out.println("alias=[" + keyAlias + "]");
			}
			pk = (PrivateKey) keyStore.getKey(keyAlias, pfxPasswd.toCharArray());
			fis.close();
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取cer公钥
	 * 
	 * @param getPulicKey
	 * @return
	 */
	public PublicKey getPulicKey(String cerPath) {

		FileInputStream bais;

		try {
			CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
			bais = new FileInputStream(cerPath);
			X509Certificate cert = (X509Certificate) certificatefactory.generateCertificate(bais);
			return cert.getPublicKey();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
