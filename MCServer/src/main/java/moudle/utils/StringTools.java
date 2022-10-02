package moudle.utils;

import cn.hutool.core.codec.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
public class StringTools extends org.apache.commons.lang3.StringUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 空字符串 */
    public static final String NULLSTR = "";

    /** 下划线 */
    public static final char SEPARATOR = '_';

    /** 分号 */
    public static final String SEMICOLON = ";";

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue)
    {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     ** @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects)
    {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects)
    {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map)
    {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map)
    {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str)
    {
        return isNull(str) || NULLSTR.equals(str.trim()) || "null".equals(str.toLowerCase());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object)
    {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     */
    public static String trim(String str)
    {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str 字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = str.length() + start;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (start > str.length())
        {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str 字符串
     * @param start 开始
     * @param end 结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String s)
    {
        if (s == null)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1))
            {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c))
            {
                if (!upperCase || !nextUpperCase)
                {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            }
            else
            {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs)
    {
        if (str != null && strs != null)
        {
            for (String s : strs)
            {
                if (str.equalsIgnoreCase(trim(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name)
    {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty())
        {
            // 没必要转换
            return "";
        }
        else if (!name.contains("_"))
        {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels)
        {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty())
            {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 字符转base64
     * @param s
     * @return
     */
    public static String getBASE64(String s)
    {
        return s == null ?"": Base64.encode(s.getBytes());
    }

    /**
     * base解码为字符
     * @param s
     * @return
     */
    public static String getDecoderBASE64(String s)
    {
        if(s == null) {
            return "";
        }
        try {
            byte[] b = Base64.decode(s);
            return new String(b, StandardCharsets.UTF_8); } catch (Exception e) {
        }
        return "";
    }

    /**
     * 追加'0'
     * @param number
     * @param length
     * @return
     */
    public static String format(long number, int length)
    {
        return String.format("%0" + length + "d", new Object[] { Long.valueOf(number) });
    }

    /**
     * 删除'0'
     * @param str
     * @return
     */
    public static String remove00(String str)
    {
        char[] charArray = str.toCharArray();
        int index = -1;
        for (int i = charArray.length - 1; i > 0; i -= 2) {
            if ((charArray[i] != '0') || (charArray[(i - 1)] != '0')) {
                index = i;
                break;
            }
        }
        return index < 0 ? "" : String.valueOf(charArray, 0, index + 1);
    }

    /**
     * 将对象转为str
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return obj == null || "null".equals(String.valueOf(obj)) ? "" : String.valueOf(obj);
    }

    /**
     *
     * @Description:按字节数截取字符串
     * @param str
     * @param begin
     * @param length（当前虚拟机字符集想对应的切割字节长度，各字符集的中文占比不同）
     * @return
     * @throws Exception
     */
    public static String byteSubString(String str,int begin,int length) throws Exception {
        if(begin > str.getBytes().length) {
            throw new Exception("开始下标超过总字节数！");
        }else if(begin == str.getBytes().length) {
            return "";
        }

        if(length == 0) {
            return "";
        }

        int index = 0;
        int sum = 0;
        int ofset = 0;
        for(int i = 0; i < str.length(); i++) {
            String s = Character.toString(str.charAt(i));
            byte[] bytes = s.getBytes();
            if(sum + bytes.length < begin) {
                sum += bytes.length;
            }else if(sum + bytes.length == begin) {
                sum += bytes.length;
                index = i + 1;
                ofset = sum - begin;
                break;
            }else {
                if(begin == 0) {
                    index = 0;
                }else {
                    sum += bytes.length;
                    index = i + 1;
                    ofset = sum - begin;
                }
                break;
            }
        }

        System.out.println("from:"+index + "\tofset:"+ ofset +"\tsublength:"+(length-ofset));

        int count = 0;
        String result = "";
        for(int i = index; i < str.length(); i++) {
            String s = Character.toString(str.charAt(i));
            byte[] bytes = s.getBytes();
            if(count + bytes.length <= length-ofset) {
                result += s;
                count += bytes.length;
            }else {
                return result;
            }

        }

        return result;
    }

    /**
     *
     * @Description:按字节数截取字符串(一个中文占两个字节，Unicode)
     * @param str
     * @param begin
     * @param length
     * @return
     * @throws UnsupportedEncodingException
     */
    @Deprecated
    public static String bSubString(String str, int begin, int length) throws UnsupportedEncodingException {
        byte[] bytes;
        bytes = str.getBytes("Unicode");
        int z = begin = 2 * begin + 2;
        int n = 0, count = 0;
        for (; begin < bytes.length && n < length; n++, begin++) {
            if (bytes[begin] == 0) {
                n--;
            }
            count++;
        }

        if (count % 2 == 1) {
            if (bytes[count - 1] != 0) {
                count--;
            } else if (bytes[count - 1] == 0) {
                count++;
            }
        }
        return new String(bytes, z, count, "Unicode");
    }


    /**
     * 按指定的字节数截取字符串（一个中文字符占3个字节，一个英文字符或数字占1个字节,UTF-8）
     * @param sourceString 源字符串
     * @param cutBytes 要截取的字节数
     * @return
     */
    @Deprecated
    public static String cutString(String sourceString, int cutBytes) {
        if(sourceString == null || "".equals(sourceString.trim())) {
            return "";
        }

        int lastIndex = 0;
        boolean stopFlag = false;

        int totalBytes = 0;
        for(int i=0; i<sourceString.length(); i++) {
            String s = Integer.toBinaryString(sourceString.charAt(i));
            if(s.length() > 8) {
                totalBytes += 3;
            }else{
                totalBytes += 1;
            }

            if(!stopFlag) {
                if(totalBytes == cutBytes){
                    lastIndex = i;
                    stopFlag = true;
                }else if(totalBytes > cutBytes) {
                    lastIndex = i - 1;
                    stopFlag = true;
                }
            }
        }

        if(!stopFlag){
            return sourceString;
        }else{
            return sourceString.substring(0, lastIndex + 1);
        }
    }

    public static boolean containsN(String s) {
        if(s.isEmpty() || "null".equals(s) || s.contains("\\N")) {
            return true;
        }
        return false;
    }

    /**
     * 将数组按指定字符串连接成为一个字符串(请使用org.apache.commons.lang下的StringUtils工具)
     * @param ary
     * @param split_str
     * @return
     */
    @Deprecated
    public static String join(Object[] ary, String split_str) {
        String result = "";
        for(Object o : ary) {
            result += o + split_str;
        }
        result = result.substring(0,result.length()-1);
        return result;
    }

    /**
     * 将数组按指定字符串连接成为一个字符串(请使用org.apache.commons.lang下的StringUtils工具)
     * @param ary
     * @param split_str
     * @return
     */
    @Deprecated
    public static String join(int[] ary, String split_str) {
        String result = "";
        for(Object o : ary) {
            result += o + split_str;
        }
        result = result.substring(0,result.length()-1);
        return result;
    }

    /**
     * 替换一对marks标识符之间的某个字符 如：替换字符串引号中的逗号
     * @param v 输入字符串
     * @param from 起始下标，如需全部替换从0开始
     * @param marks 一对marks标识符
     * @param target 需要替换的字符串
     * @param relace_str 替换字符串
     * @return
     */
    public static String replaceMarks(String v,int from,String marks,String target,String relace_str) {
        int begin = v.indexOf(marks,from);
        int end = v.indexOf(marks, begin+1);
        if(begin < 0 || end < 0) {
            return v;
        }
        String need_replace_str = v.substring(begin, end+1);

        while(need_replace_str.contains(",")) {
            String replace_str = need_replace_str.replaceAll(target, relace_str);
            v = v.replace(need_replace_str, replace_str);
            String result = replaceMarks(v,end+1,marks,target,relace_str);
            return result;
        }
        return v;
    }

    /**
     * 替换一对beginMarks,endMarks标识符之间的某个字符 如：替换字符串引号中的逗号
     * @param v 输入字符串
     * @param from 起始下标，如需全部替换从0开始
     * @param beginMarks 开始标识符
     * @param endMarks 结束标识符
     * @param target 需要替换的字符串
     * @param relace_str 替换字符串
     * @return
     */
    public static String replaceMarks(String v,int from,String beginMarks,String endMarks,String target,String relace_str) {
        int begin = v.indexOf(beginMarks,from);
        int end = v.indexOf(endMarks, begin+1);
        if(begin < 0 || end < 0) {
            return v;
        }
        String need_replace_str = v.substring(begin, end+1);

        while(need_replace_str.contains(",")) {
            String replace_str = need_replace_str.replaceAll(target, relace_str);
            v = v.replace(need_replace_str, replace_str);
            String result = replaceMarks(v,end+1,beginMarks,endMarks,target,relace_str);
            return result;
        }
        return v;
    }

    /**
     * 下划线命名转为驼峰命名
     * @param para
     * @return
     */
    public static String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String [] a = para.split("_");
        for(String s:a){
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /**
     * 驼峰命名转为下划线命名
     * @param para
     * @return
     */
    public static String humpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        //定位
        int temp=0;
        if (!para.contains("_")) {
            for(int i=0;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 添加url参数
     * @param url
     * @param param
     * @return
     */
    public static String addUrlParams(String url,Object param) throws Exception {
        Class<? extends Object> c = param.getClass();

        String s = url + "?";
        Field[] declaredFields = c.getDeclaredFields();
        for(Field f : declaredFields) {
            f.setAccessible(true);
            String name = f.getName();
            Object value = f.get(param);
            if(value != null) {
                s += name + "=" + value + "&";
            }
        }
        if(s.endsWith("&")) {
            s = s.substring(0, s.length()-1);
        }

        return s;
    }

    /**
     * 获取方法名根据属性名
     * @param pre
     * @param fieldName
     * @return
     */
    public static String getMethodNameByFieldName(String pre,String fieldName) {
        String s = fieldName.charAt(0)+"";

        char s1 = fieldName.charAt(1);
        String upperCase = s;
        if(s1 >= 'A' && s1 <= 'Z') {
            //第二位为大写不转
        }else {
            upperCase = s.toUpperCase();
        }
        String result = pre+upperCase + fieldName.substring(1, fieldName.length());
        return result;
    }

    public static String nullToEmpty(Object o) {
        if(o == null) {
            return "";
        }
        return o.toString();
    }

    /**
     * 替换-为空
     * @param str
     * @return
     */
    public static String replaceEnter(String str){
        String reg ="[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 根据当前编码获取下一个编码（2位字符，00-ZZ）
     * @param curCode
     * @return
     */
    public static String getNext2DigitCode(String curCode) {
        char[] codeChar = curCode.toCharArray();
        int firstCode = codeChar[0];
        int secondCode = codeChar[1];
        if ((secondCode + 1 > 57) && (secondCode < 65)) {
            secondCode = 65;
        } else if (secondCode + 1 > 90) {
            secondCode = 48;
            firstCode++;
        } else {
            secondCode++;
        }

        if ((firstCode > 57) && (firstCode < 65)){
            firstCode = 65;
        }else if (firstCode > 90) {
            firstCode = 48;
        }
        byte[] asicllBtye = { (byte)firstCode, (byte)secondCode };
        return new String(asicllBtye);
    }

    /**
     * 替换null
     * @param str
     * @return
     * @author: zdy
     * @date: 2020/8/13
     */
    public static String replaceNull(String str) {
        return StringUtils.isNotEmpty(str) ? str : "";
    }

    /**
     * 字段"_"转驼峰
     * @param oldKey
     * @return java.lang.String
     **/
    public static String keyTransform(String oldKey){
        oldKey = oldKey.toLowerCase();
        Matcher matcher = linePattern.matcher(oldKey);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        StringBuffer covKey = matcher.appendTail(sb);
        return covKey.toString();
    }

    public static void main(String[] args) {
        System.out.println(getNext2DigitCode("ZZ"));
    }
}
