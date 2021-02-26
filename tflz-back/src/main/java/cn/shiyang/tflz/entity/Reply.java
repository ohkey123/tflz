package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value = "Reply对象", description = "")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Reply id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "目标倡议id")
    @Min(value = 1, message = "Propose id不能小于1")
    private Integer sid;

    @ApiModelProperty(value = "发起用户id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer uid;

    @ApiModelProperty(value = "回复详细文章")
    @Length(min = 1, message = "Reply文章不能为空")
    private String detail;

    @ApiModelProperty(value = "回复创建时间")
    @NotNull(message = "Reply创建时间不能为空")
    private Date timeCreate;


}
