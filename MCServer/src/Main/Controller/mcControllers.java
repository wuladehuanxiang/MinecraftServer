package Main.Controller;


import Main.Class.Entity.category;
import Main.Class.Entity.item;
import Main.Class.Entity.type;
import Main.Main;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mc")
public class mcControllers {
    @CrossOrigin
    @PostMapping("/operateCategory")
    public Object operateCategroy(@RequestBody String data) throws UnsupportedEncodingException {
        System.out.println(data);
        JSONObject jsonObject = JSON.parseObject(data);
        //若包含uuid 说明是操作
        if (jsonObject.containsKey("uuid")) {
            String uuid = jsonObject.getString("uuid");
            if (jsonObject.containsKey("delete")) {
                //如果是删除
                category thedelete = null;
                for (category c : Main.staticData.categories) {
                    if (c.uuid.equals(uuid)) {
                        thedelete = c;
                    }
                }
                if (thedelete != null) {
                    Main.staticData.categories.remove(thedelete);
                    return "删除成功";
                }
                return "此目录不存在";
            }


            boolean hasThisCategory = false;
            for (category c : Main.staticData.categories) {
                if (c.uuid.equals(uuid)) {
                    hasThisCategory = true;
                }
            }

            //若所有的目录中拥有这个目录 则说明是修改目录
            if (hasThisCategory == true) {

                return "更改了一个目录";
            }


            return "未进行操作";

        } else if (!jsonObject.containsKey("uuid") && jsonObject.containsKey("name")) {
            String name = jsonObject.getString("name");
            category category = new category();
            category.name = name;
            Main.staticData.categories.add(category);
            return "成功新增此目录";
        } else {
            return Main.staticData.categories;
        }


    }

    @CrossOrigin
    @PostMapping("/operateType")
    public Object operateType(@RequestBody String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        //若拥有categroyUUID
        if (jsonObject.containsKey("categoryUUID")) {
            //判断是否有这个类别
            category category = null;
            for (category c : Main.staticData.categories
            ) {
                if (c.uuid.equals(jsonObject.getString("categoryUUID"))) {
                    category = c;
                }
            }
            if (category != null && jsonObject.containsKey("uuid")) {
                //若有且Uuid不为空 啧说明为更改或者删除
                String uuid = jsonObject.getString("uuid");

                type type = null;
                for (type t : category.types) {
                    if (t.uuid.equals(uuid)) {
                        type = t;
                    }
                }


                if (jsonObject.containsKey("delete") && type != null) {
                    //如果是删除
                    category.types.remove(type);
                    return "删除成功";
                }


                //若所有的目录中拥有这个目录 则说明是修改目录
                if (type != null) {

                    return "更改了一个类型";
                }


                return "未进行操作";

            } else if (category != null && !jsonObject.containsKey("uuid") && jsonObject.containsKey("name")) {
                //新增一个类型
                String name = jsonObject.getString("name");
                type type = new type();
                type.name = name;
                category.types.add(type);
                return "成功新增此类型";
            } else {
                return category.types;
            }
        }


        return "未进行操作";

    }

    @CrossOrigin
    @PostMapping("/operateItem")
    public Object operateItem(@RequestBody String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        //若拥有categroyUUID
        if (jsonObject.containsKey("categoryUUID") && jsonObject.containsKey("TypeUUID")) {
            //判断是否有这个类别
            category category = null;
            for (category c : Main.staticData.categories
            ) {
                if (c.uuid.equals(jsonObject.getString("categoryUUID"))) {
                    category = c;
                }
            }

            type type = null;
            if (category != null) {

                for (type t : category.types
                ) {
                    if (t.uuid.equals(jsonObject.getString("TypeUUID"))) {
                        type = t;
                    }
                }
            }

            if (type != null) {

                if (jsonObject.containsKey("uuid")) {
                    //若有且Uuid不为空 啧说明为更改或者删除
                    String uuid = jsonObject.getString("uuid");
                    item item = null;
                    for (item i : type.items) {
                        if (i.uuid.equals(uuid)) {
                            item = i;
                        }
                    }

                    if (jsonObject.containsKey("delete")) {
                        //如果是删除
                        if (item != null) {
                            type.items.remove(item);
                            return "删除成功";
                        }
                        return "此类型不存在";
                    }


                    //若所有的目录中拥有这个目录 则说明是修改目录
                    if (item != null) {

                        return "更改了一个物品";
                    }
                    return "未进行操作";
                } else if (!jsonObject.containsKey("uuid") && jsonObject.containsKey("name")) {
                    //新增一个类型
                    String name = jsonObject.getString("name");
                    item item = new item();
                    item.name = name;
                    type.items.add(item);
                    return "成功新增此类型";
                } else {
                    return type.items;
                }
            }
            return "未进行操作";


        }


        return "未进行操作";

    }


}
