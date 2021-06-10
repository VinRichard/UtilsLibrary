package fz.vrd.utils;

import androidx.appcompat.app.AppCompatActivity;

import fz.vrd.library.aes.Factory;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/18 9:33 <br/>
 * <b>说明：{ } <br/>
 */
public class Abc extends AppCompatActivity implements Factory {


    @Override
    public String decryption(String key) {
        System.out.println("=====decryption=========" + key);
        return null;
    }

    @Override
    public String encryption(String msg) {
        System.out.println("=======encryption=======" + msg);
        return null;
    }
}
