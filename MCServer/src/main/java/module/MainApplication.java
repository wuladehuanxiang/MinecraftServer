package module;

import module.data.StaticData;
import module.common.utils.InitUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;


/**
 * 程序入口
 *
 * @author
 */
@SpringBootApplication
@MapperScan("module.dao")
@Configuration
public class MainApplication {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, SQLException {
        SpringApplication.run(MainApplication.class, args);
        InitUtil.init();

        for (Class c : StaticData.allClasses) {
            System.out.println(c.getName());
        }
    }
}
