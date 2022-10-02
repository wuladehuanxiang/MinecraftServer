package moudle.utils;


import moudle.data.StaticData;

import java.io.IOException;
import java.util.*;


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
     * 将所有的CLASS缓存到内存中
     */
    public static void initClasses() {
        StaticData.allClasses = ReflectUtil.getClasses("moudle.entity");
        StaticData.mapperClasses = ReflectUtil.getClasses("moudle.dao");


    }

    public void initFiles() {

    }

    public void initUser() {

    }

    public static void initEvents() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {


    }


}
