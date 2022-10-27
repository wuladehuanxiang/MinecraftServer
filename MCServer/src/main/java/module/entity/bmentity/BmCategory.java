package module.entity.bmentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import module.common.base.BaseEntity;

/**
 * @author: wula
 * @date: 2022/09/12
 * 这个类 是所有要展示的目录大类
 **/
@TableName("bm_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmCategory extends BaseEntity<BmCategory> {

    /**
     * 唯一标识符
     */
    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 板块名称
     */
    @TableField("name")
    private String name;

    /**
     * 图标
     */
    @TableField("image")
    private String image;

    @TableField("description")
    private String description;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
