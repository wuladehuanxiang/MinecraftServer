package Main.Utils;

import Main.Class.Entity.category;
import Main.Class.Entity.item;
import Main.Class.Entity.type;
import Main.Data.StaticData;
import Main.Main;
import Main.Class.Entity.item;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.json.JSONParser;


import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {


    public static String loadFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
//        将 fileInputStream（字节流） 流作为参数，转为InputStreamReader（字符流）
        InputStreamReader reader = new InputStreamReader(fileInputStream);

//      将 字符流（参数）转为字符串流，带缓冲的流读取，默认缓冲区8k
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuffer stringBuffer = new StringBuffer();

        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }
        fileInputStream.close();
        bufferedReader.close();
        return stringBuffer.toString();
    }

    /**
     * 序列化函数
     *
     * @param object 需要序列化的对象
     */
    public static void writeObj(Object object, File file) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file.getPath()));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param file 需要序列化的对象的文件
     * @return 反序列化
     */
    public static Object readObj(File file) {
        Object object = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file.getPath()));
            try {
                object = (Object) objectInputStream.readObject();
                for (Field field : object.getClass().getFields()) {
                    System.out.println("reading field from object" + field.getName() + ": " + field.toString());
                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void saveAll() throws IOException {
        saveEvents();
        saveMC();
    }

    public static void saveEvents() throws IOException {
        writeObj(Main.staticData.perMissionData, new File("./permission"));
    }

    public static void initCategory() throws IOException {

        File file = new File("./Category");
        if (!file.exists()) {
            file.mkdir();
        } else {
            List<category> categoryList = new ArrayList<>();
            System.out.println("正在读取所有大类");
            for (File firstFloor : file.listFiles()
            ) {
                category category = new category();
                category.uuid = firstFloor.getName().split("'")[1];
                category.name = firstFloor.getName().split("'")[0];

                System.out.println("重载了一个目录名" + category.name + " id" + category.uuid);
                //目录
                if (firstFloor.isDirectory()) {

                    //种类
                    for (File secondFloor : firstFloor.listFiles()
                    ) {
                        type type = new type();
                        type.uuid = secondFloor.getName().split("'")[1];
                        type.name = secondFloor.getName().split("'")[0];

                        //种类
                        for (File item : secondFloor.listFiles()
                        ) {
                            item object = JSON.parseObject(loadFile(item.getAbsolutePath()), item.class);
                            object.uuid = item.getName().split("'")[1];
                            object.name = item.getName().split("'")[0];
                            type.items.add(object);
                            System.out.println("  重载了一个实体名" + object.name + " id" + object.uuid);
                        }
                        category.types.add(type);
                        System.out.println(" 重载了一个种类名" + type.name + " id" + type.uuid);
                    }
                    categoryList.add(category);
                }
            }
            Main.staticData.categories = categoryList;
        }

    }

    public static void saveMC() throws IOException {
        File file = new File("./Category");
        if (!file.exists()) {
            file.mkdir();
            System.out.println("保存根目录不存在 重新创建");
        }

        for (category c : Main.staticData.categories
        ) {
            File cFile = new File("./Category/" + c.name + "'" + c.uuid);
            if (!cFile.exists()) {
                cFile.mkdir();
                System.out.println("创建目录" + cFile.getName());
            }

            for (type t : c.types
            ) {
                File tFile = new File("./Category/" + c.name + "'" + c.uuid + "/" + t.name + "'" + t.uuid);
                if (!tFile.exists()) {
                    tFile.mkdir();
                    System.out.println("创建种类" + cFile.getName());
                }
                for (item i : t.items
                ) {
                    File iFile = new File("./Category/" + c.name + "'" + c.uuid + "/" + t.name + "'" + t.uuid + "/" + i.name + "'" + i.uuid + ".json");
                    if (!tFile.exists()) {
                        try {
                            tFile.createNewFile();
                            System.out.println("创建实体" + cFile.getName());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        FileWriter writer = new FileWriter(iFile);
                        BufferedWriter out = new BufferedWriter(writer);
                        out.write(JSONObject.toJSONString(i));
                        out.flush(); // 把缓存区内容压入文件
                        out.close();
                    }

                }
            }

        }
    }


}
