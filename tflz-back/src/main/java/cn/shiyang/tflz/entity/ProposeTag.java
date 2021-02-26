package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ProposeTag对象", description="")
public class ProposeTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "倡议绑定标签的唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "ProposeTag id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "倡议id")
    @Min(value = 1, message = "Propose id不能小于1")
    private Integer sid;

    @ApiModelProperty(value = "标签id")
    @Min(value = 1, message = "Tag id不能小于1")
    private Integer tid;


}
