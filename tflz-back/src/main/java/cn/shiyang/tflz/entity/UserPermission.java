package cn.shiyang.tflz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

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
@ApiModel(value="UserPermission对象", description="")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户与权限关系的唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "UserPermission id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @Min(value = 1, message = "User id不能小于1")
    private Integer uid;

    @ApiModelProperty(value = "权限id")
    @Min(value = 1, message = "Permission id不能小于1")
    private Integer pid;


}
