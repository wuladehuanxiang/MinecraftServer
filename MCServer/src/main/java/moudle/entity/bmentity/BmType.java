package moudle.entity.bmentity;

/**
 * @author: wula
 * @date: 2022/09/12
 **/
public class BmType {
    /**
     * 种类的名称
     */
    private String name;

    /**
     * 唯一标识符
     */
    private String uuid;

    /**
     * 图标
     */
    private String icon;

    /**
     * 内容 需要是一个富文本编辑器
     */
    private String content;


    //这个类别下面拥有很多的 BmItem
}
