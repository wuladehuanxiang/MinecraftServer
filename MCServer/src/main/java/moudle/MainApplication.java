package moudle;

import com.alibaba.fastjson.JSON;
import moudle.controller.CommonController;
import moudle.data.StaticData;
import moudle.entity.RequestInfo;
import moudle.entity.User;
import moudle.service.impl.CommonServiceImpl;
import moudle.utils.InitUtil;
import moudle.utils.ReflectUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;


/**
 * 程序入口
 *
 * @author
 */
@SpringBootApplication
@MapperScan("moudle.dao")
public class MainApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        SpringApplication.run(MainApplication.class, args);
        InitUtil.init();


//        RequestInfo requestInfo = new RequestInfo();
//        requestInfo.setRequestType(RequestInfo.requestType.request);
//        requestInfo.setClassName("User");
//        requestInfo.setJsonString("aaa");
//        System.out.println(JSON.toJSON(requestInfo).toString());
//        System.out.println(JSON.toJSON(new User() {{
//            this.setAccount("492397708");
//            this.setName("wula");
//            this.setPassword("123456");
//            this.setUuid(UUID.randomUUID().toString());
//        }}).toString());
//        ;


        ReflectUtil.getObjectFJSON("{\"password\":\"123456\",\"name\":\"wula\",\"uuid\":\"9f9643c3-81c1-4df2-a819-47a3a1262883\",\"account\":\"492397708\"}", "User");

        for (Class c : StaticData.allClasses
        ) {

            System.out.println(c.getName());

        }


    }
}
