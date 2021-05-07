## 一.Android 7.0及以上版本,文件权限配置更新为FileProvider的方式: ##
      1. 在项目的AndroidManifest.xml添加相关配置，示例如下：
       ```
            <provider
                android:name="android.support.v4.content.FileProvider"
                android:authorities="${applicationId}.fileprovider"
                android:exported="false"
                android:grantUriPermissions="true">
                <meta-data
                    android:name="android.support.FILE_PROVIDER_PATHS"
                    android:resource="@xml/file_provider_paths" />
            </provider>
         ```
      2. 在res/xml目录（如果没有xml目录，则新建一个）下，添加文件file_provider_paths.xml，内容如下：
       ```
            <?xml version="1.0" encoding="utf-8"?>
            <paths xmlns:android="http://schemas.android.com/apk/res/android">
                <external-files-path name="sharedata" path="shareData/"/>
            </paths>
        ```
        external-files-path表示通过 Context.getExternalFilesDir(null) 接口获取到的目录下的文件才可被共享，其他未配置的路径均不可被分享。同样的节点可以配置多个，以支持多个不同的子目录.
        如下所示：
         ```
            <?xml version="1.0" encoding="utf-8"?>
            <paths xmlns:android="http://schemas.android.com/apk/res/android">
                <external-files-path name="sharedata" path="shareData/"/>
                <external-files-path name="sharedata2" path="shareData2/"/>
            </paths>
          ```
        paths内部还支持节点配置其他的路径，比如：
            files-path，对应于 Context.getFilesDir() 获取到的目录
            cache-path，对应于 Context.getCacheDir() 获取到的目录

        还有一些其他可配置的路径，开发者可自行了解使用。

二.软件可见性适配(Android 11)
    1. 在主工程的AndroidManifest.xml 中增加 标签.
        ```
        <manifest package="com.example.app">
              // 在应用的AndroidManifest.xml添加如下<queries>标签
            <queries>
                <package android:name="com.tencent.mm" />   // 指定微信包名
            </queries>
        </manifest>
         ```
     备注:以上一和二的出处地址:https://open.weixin.qq.com/cgi-bin/announce?action=getannouncement&key=11600155960jI9EY&version=&lang=&token=