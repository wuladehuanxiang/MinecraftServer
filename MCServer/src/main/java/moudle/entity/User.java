package moudle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(value = "用户实体")
@TableName("sys_user")
public class User {
    /**
     * uuid
     */
    //@ApiModelProperty(value = "uuid")
    @TableId(value = "uuid",type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 账号(唯一)
     */
    //@ApiModelProperty(value = "账号")
    @TableField("account")
    private String account;

    /**
     * 用户名
     */
    //@ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    /**
     * 密码
     */
    //@ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;
}
