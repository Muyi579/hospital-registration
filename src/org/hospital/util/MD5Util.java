package org.hospital.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 18:21
 * @Description MD5加密工具类
 */
public class MD5Util {
    public MD5Util() {
    }

    public static String getMd5(Object password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(((String) password).getBytes());
            byte[] b = md.digest();
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMd5("admin"));
    }
}
