package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Notice对象", description="")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "Notice id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "倡议id")
    @Min(value = 1, message = "Propose id不能小于1")
    private Integer sid;

    @ApiModelProperty(value = "用户id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer uid;

    @ApiModelProperty("发起用户id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer Suid;

    @ApiModelProperty(value = "为1表示未读，为0表示已读")
    @TableField("isUnread")
    @NotNull(message = "标志位不能为空")
    private Boolean isUnread;

    @ApiModelProperty(value = "回复创建时间")
    @NotNull(message = "创建时间不能为空")
    private Date timeCreate;


}
