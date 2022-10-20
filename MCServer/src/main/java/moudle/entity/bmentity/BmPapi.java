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
 * @date: 2022/10/20
 **/
@TableName("bm_papi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmPapi extends BaseEntity<BmPapi> {

    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    @TableField("player")
    private String player;

    @TableField("papi")
    private String papi;

    @TableField("value")
    private String value;
}
