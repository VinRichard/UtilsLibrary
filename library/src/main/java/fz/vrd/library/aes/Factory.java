package fz.vrd.library.aes;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/17 10:50 <br/>
 * <b>说明：{ } <br/>
 */
public interface Factory {

    /**
     * 解密
     *
     * @param key : 需要解密的秘钥
     */
    String decryption(String key);

    /**
     * 加密
     *
     * @param msg:需要加密的信息
     */
    String encryption(String msg);
}
