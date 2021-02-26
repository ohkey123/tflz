package cn.shiyang.tflz.controller;

import cn.shiyang.tflz.utils.CheckCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiyang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/tflz/utils")
@Api("实用工具前端控制器")
public class UtilsController {
    @GetMapping(value = "/getCheckCode", produces = MediaType.IMAGE_PNG_VALUE)
    @ApiOperation("为前端响应验证码")
    public byte[] getCheckCode(HttpSession session) throws IOException {
        File file = new File(System.getProperty("user.dir"), "checkCode.png");

        String checkCode = CheckCodeUtils.outputVerifyImage(800, 200, file, 4);
        session.setAttribute("checkCode", checkCode);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] ret = bis.readAllBytes();
        return ret;
    }
}
