package fz.vrd.library.aes;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * <b>类名称：MD5加密  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/17 9:48 <br/>
 * <b>说明：{
 *  MD5即Message-Digest Algorithm 5（信息-摘要算法5），用于确保信息传输完整一致。
 *  是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
 *  将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
 *  广泛用于加密和解密技术，常用于文件校验。校验？不管文件多大，经过MD5后都能生成唯一的MD5值。
 *  好比现在的ISO校验，都是MD5校验。怎么用？当然是把ISO经过MD5后产生MD5的值。
 *  一般下载linux-ISO的朋友都见过下载链接旁边放着MD5的串。
 *  就是用来验证文件是否一致的。
 * <p>
 * } <br/>
 */
class MD5 implements Factory {

    private static final String KEY_MD5 = "MD5";

    @Override
    public String decryption(String key) {

        return "";
    }

    @Override
    public String encryption(String info) {
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance(KEY_MD5);
            byte[] inputData = info.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(1, md.digest());
            return bigInteger.toString(16).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
