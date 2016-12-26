package com.ecnu.psf;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Marty Pang on 2016/12/26.
 */

public class PropertyManager {
    // 是否已经初始化过 false表示未初始化过 ；true表示已经初始化。
    private static boolean m_bInited = false;
    // 配置文件中的配置项目。
    protected static Properties m_vProperties = new Properties();
    static {
        // 加载配置文件
        loadPropertyFile("config");
    }

    // 加载配置文件
    protected static void loadPropertyFile(String propertyFile) {
        // 如果已经初始化过了，则不再进行初始化。
        if (m_bInited)
            return;
        ResourceBundle bundle = null;
        // 把配置信息转换成Properties
        try {
            // 读取配置文件
            bundle = ResourceBundle.getBundle(propertyFile);
            Enumeration<?> vEnumeration = bundle.getKeys();
            Object oKey = null;
            Object oValue = null;
            while (vEnumeration.hasMoreElements()) {
                oKey = vEnumeration.nextElement();
                oValue = bundle.getString(oKey.toString());
                m_vProperties.put(oKey, oValue);
            }
            bundle = null;
            m_bInited = true;
        } catch (Exception e) {
            bundle = null;
            m_bInited = false;
        }

    }

    public static Properties loadProperty(String propertyFile) {
        Properties property = new Properties();
        ResourceBundle bundle = null;
        // 把配置信息转换成Properties
        try {
            // 读取配置文件
            bundle = ResourceBundle.getBundle(propertyFile);
            Enumeration<?> vEnumeration = bundle.getKeys();
            Object oKey = null;
            Object oValue = null;
            while (vEnumeration.hasMoreElements()) {
                oKey = vEnumeration.nextElement();
                oValue = bundle.getString(oKey.toString());
                property.put(oKey, oValue);
            }
        } catch (Exception e) {
        }
        return property;
    }

    // 根据key获取配置信息的内容
    public static String getProperty(String key) {
        return m_vProperties.getProperty(key);
    }
}
