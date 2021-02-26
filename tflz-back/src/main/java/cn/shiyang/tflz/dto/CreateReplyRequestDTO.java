package cn.shiyang.tflz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="创建Reply时需要的请求体", description="")
public class CreateReplyRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "目标倡议id")
    @Min(value = 1, message = "目标倡议id不能小于1")
    private Integer sid;

    @ApiModelProperty(value = "发起用户id")
    @Min(value = 1, message = "发起用户id不能小于1")
    private Integer uid;

    @ApiModelProperty(value = "回复详细文章")
    @Length(min = 1, message = "回复文章不能为空")
    private String detail;

    @ApiModelProperty("验证码")
    @NotNull(message = "验证码不能为空")
    private String checkCode;

}
