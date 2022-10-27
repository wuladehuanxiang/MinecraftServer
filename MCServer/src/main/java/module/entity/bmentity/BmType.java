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
 **/
@TableName("bm_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmType extends BaseEntity<BmType> {

    /**
     * 唯一标识符
     */
    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 种类的名称
     */
    @TableField("name")
    private String name;

    /**
     * 所属种类的uuid
     */
    @TableField("category_uuid")
    private String categoryUuid;

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

    public String getCategoryUuid() {
        return categoryUuid;
    }

    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
