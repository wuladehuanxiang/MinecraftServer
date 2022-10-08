package moudle.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 *  基础实体类
 * </p>
 * @author : Dragon丿Z
 * @date : 2022/09/09 14:20
 */

@Setter
@Getter
public class BaseEntity<T extends Model<?>> extends Model<T> {
    /**
     * 删除状态 0未删除 1已删除
     */
    //@ApiModelProperty(value = "删除状态 0未删除 1已删除")
    @TableField("del_flag")
    @TableLogic
    private String delFlag="0";

    /**
     * 创建者
      */
    //@ApiModelProperty(value = "创建者")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新者
      */
    //@ApiModelProperty(value = "更新者")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 创建时间
      */
    //@ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
      */
    //@ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
