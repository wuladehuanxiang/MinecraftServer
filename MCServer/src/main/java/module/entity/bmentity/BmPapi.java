package module.entity.bmentity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import module.common.base.BaseEntity;

/**
 * @author: wula
 * @date: 2022/10/20
 **/
@TableName("bm_papi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmPapi extends BaseEntity<BmPapi> {

    /**
     * 占位符 placeholderApi
     */
    @TableField("papi")
    private String papi;

    /**
     * 玩家
     */
    @TableField("player")
    private String player;

    /**
     * 玩家信息
     */
    @TableField("value")
    private String value;
}
