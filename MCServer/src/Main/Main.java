package Main;

import Main.Data.staticData;
import Main.Utils.InitUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;


/**
 * 程序入口
 */
@SpringBootApplication
public class Main {



    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        SpringApplication.run(Main.class, args);
        InitUtil.init();

        for (Class c: staticData.allClasses
             ) {
            System.out.println(c.getName());

        }
//        SpringApplication.run(Main.class, args);


    }

}
