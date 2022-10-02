package moudle.entity.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 通用查询条件参数
 * @Author: Dragon丿Z
 * @Date: 2022/9/11 17:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params implements Serializable {

    /**
     * 表字段
     */
    private String tableField;
    /**
     * 对应属性
     */
    private String property;
    /**
     * 查询参数
     */
    private String param;

    public String getTableField() {
        return tableField;
    }

    public void setTableField(String tableField) {
        this.tableField = tableField;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
