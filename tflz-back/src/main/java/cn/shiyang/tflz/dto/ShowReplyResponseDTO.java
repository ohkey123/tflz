package cn.shiyang.tflz.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="响应Reply详情的对象", description="")
public class ShowReplyResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "目标倡议id")
    private Integer sid;

    @ApiModelProperty(value = "发起用户")
    private UserLoginSucceedResponseDTO  user;

    @ApiModelProperty(value = "回复详细文章")
    private String detail;

    @ApiModelProperty(value = "回复创建时间")
    private String timeCreate;
}