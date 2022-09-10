package moudle;

import moudle.controller.CommonController;
import moudle.data.StaticData;
import moudle.service.impl.CommonServiceImpl;
import moudle.utils.InitUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


/**
 * 程序入口
 *
 * @author
 */
@SpringBootApplication
@MapperScan("moudle.dao")
public class MainApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        SpringApplication.run(MainApplication.class, args);
        InitUtil.init();


        System.out.println(StaticData.allClasses.size());
        for (Class c : StaticData.allClasses
        ) {
            System.out.println(c.getName());

        }
    }
}
