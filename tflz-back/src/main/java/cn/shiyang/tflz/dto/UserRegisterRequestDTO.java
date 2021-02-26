package cn.shiyang.tflz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户注册时的请求数据")
public class UserRegisterRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户邮箱号码")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",
            message = "邮箱格式不合法")
    private String email;

    @ApiModelProperty(value = "用户名")
    @Length(min = 1, max = 12, message = "用户名的长度在[1,12]")
    private String name;

    @ApiModelProperty(value = "年龄")
    @Range(min = 1, max = 100, message = "年龄的范围在[1,100]")
    private Integer age;

    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
    private Boolean sex;

    @ApiModelProperty(value = "用户密码")
    @Length(min = 8, max = 15, message = "密码必须大于8位小于15位")
    private String password;

    @ApiModelProperty(value = "用户输入的验证码")
    @NotNull(message = "验证码不嫩为空")
    private String checkCode;
}
