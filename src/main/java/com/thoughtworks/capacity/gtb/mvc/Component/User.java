package com.thoughtworks.capacity.gtb.mvc.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$", message = "用户名不合法,只能由字母、数字或下划线组成")
    @NotEmpty(message = "用户名不为空")
    @Size(min = 3, max = 10, message = "用户名不合法,长度3-10位")
    private String username;

    @NotEmpty(message = "密码不为空")
    @Size(min = 5, max = 12, message = "密码不合法,长度5-12位")
    private String password;

    @Email(message = "邮箱地址不合法") // 好像不准确
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱地址不合法")
    private String email;
}
