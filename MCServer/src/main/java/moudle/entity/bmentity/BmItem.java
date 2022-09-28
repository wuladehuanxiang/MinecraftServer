package moudle.entity.bmentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import moudle.common.base.BaseEntity;

/**
 * @author: wula
 * @date: 2022/09/12
 * 物品的描述
 */
@TableName("bm_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmItem extends BaseEntity<BmItem> {

    /**
     * 唯一标识符
     */

    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 板块的UUID
     */
    @TableField("category_uuid")
    private String categoryUuid;

    /**
     * 所属种类的UUID
     */
    @TableField("type_uuid")
    private String typeUuid;

    /**
     * 图片
     */
    @TableField("image")
    private String image;

    /**
     * 描述
     */
    @TableField("description")
    private String description;


    //选用
    /**
     * 属性
     */
    @TableField("attributes")
    private String attributes;

    /**
     * 组成
     */
    @TableField("consist")
    private String consist;

    /**
     * 区域
     */
    @TableField("region")
    private String region;

    /**
     * 战利品
     */
    @TableField("spoils")
    private String spoils;


}
