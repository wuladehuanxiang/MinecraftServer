package Main.Utils;


import Main.Data.staticData;
import Main.Main;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class InitUtil {


    public static void init() throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        InitUtil.initEvents();
        InitUtil.initClasses();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Thread() {{
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }}.run();
            }
        }, 0, 10000);
    }

    /**
     *  将所有的CLASS缓存到内存中
     */
    public static void initClasses() {
        staticData.allClasses = ReflectUtil.getClasses("Main.Class");
    }

    public void initFiles() {

    }

    public void initUser() {

    }

    public static void initEvents() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {


    }


}
