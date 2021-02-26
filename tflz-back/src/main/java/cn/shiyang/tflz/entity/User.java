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
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    @Min(value = 1, message = "User id不能小于1")
    private Integer id;

    @ApiModelProperty(value = "用户邮箱号码")
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",
            message = "邮箱格式不合法")
    private String email;

    @ApiModelProperty(value = "用户名")
    @Length(min = 1, max = 12, message = "User name的长度在[1,12]")
    private String name;

    @ApiModelProperty(value = "年龄")
    @Range(min = 1, max = 100, message = "User age的范围在[1,100]")
    private Integer age;

    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
    private Boolean sex;

    @ApiModelProperty(value = "用户密码")
    @NotNull
    private String password;

    @ApiModelProperty(value = "绿色宣言")
    @Length(min = 1, max = 50, message = "绿色宣言的长度在[1,50]")
    private String declaration;

    @ApiModelProperty(value = "用户账户创建时间")
    @NotNull(message = "User账户创建时间不能为空")
    private Date timeCreate;

    @ApiModelProperty(value = "用户账户最后一次修改(包括参与讨论)时间")
    @NotNull(message = "User账户修改时间不能为空")
    private Date timeModify;


}
