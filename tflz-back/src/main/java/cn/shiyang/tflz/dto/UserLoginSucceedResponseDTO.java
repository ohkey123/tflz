package cn.shiyang.tflz.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "用户登录成功后返回给前端的UserInfo对象", description = "")
public class UserLoginSucceedResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    private Integer id;

    @ApiModelProperty(value = "用户邮箱号码")
    private String email;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Boolean sex;

    @ApiModelProperty(value = "绿色宣言")
    private String declaration;

    @ApiModelProperty(value = "用户未读消息数")
    private Integer notice;

    @ApiModelProperty(value = "用户账户创建时间")
    private String timeCreate;

    @ApiModelProperty(value = "用户账户最后一次修改(包括参与讨论)时间")
    private String timeModify;


}
