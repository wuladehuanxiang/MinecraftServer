package module.common.base;

import com.github.pagehelper.PageInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Dragon丿Z
 * @desc 分页信息
 * @date : 2022/09/09 14:20
 */

@Data
@Builder
@NoArgsConstructor
public class RespPageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 列表数据
     */
    private List<T> rows;

    public RespPageInfo(List<T> rows) {
        this.rows = rows;
        this.total = PageInfo.of(rows).getTotal();
    }

    public RespPageInfo(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
