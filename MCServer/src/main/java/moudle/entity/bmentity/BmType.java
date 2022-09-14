package moudle.entity.bmentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import moudle.common.base.BaseEntity;

/**
 * @author: wula
 * @date: 2022/09/12
 **/
@TableName("bm_type")
public class BmType extends BaseEntity<BmType> {

    /**
     * 唯一标识符
     */
    @TableId(value = "uuid",type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 种类的名称
     */
    @TableField("name")
    private String name;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 内容 需要是一个富文本编辑器
     */
    @TableField("content")
    private String content;


    //这个类别下面拥有很多的 BmItem
}
