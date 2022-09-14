package moudle.entity.bmentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import moudle.common.base.BaseEntity;

/**
 * @author: wula
 * @date: 2022/09/12
 * 这个类 是所有要展示的目录大类
 **/
@TableName("bm_category")
public class BmCategory extends BaseEntity<BmCategory> {

    /**
     * 唯一标识符
     */
    @TableId(value = "uuid",type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     *  板块名称
     */
    @TableField("name")
    private String name;

    /**
     * 图标
     */
//    @TableField("image")
    private String image;

    //这个类下面 会有很多小类 ->BmType
}
