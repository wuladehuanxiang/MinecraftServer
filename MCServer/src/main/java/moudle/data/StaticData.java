package moudle.data;

import moudle.entity.SysUser;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class StaticData {
    public static Set<Class<?>> allClasses = new HashSet<>();
    public static Set<Class<?>> mapperClasses = new HashSet<>();

    public static ConcurrentHashMap<String, String> userRole = new ConcurrentHashMap<>();


}
