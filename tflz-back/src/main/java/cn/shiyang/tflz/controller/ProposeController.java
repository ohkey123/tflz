package cn.shiyang.tflz.controller;


import cn.shiyang.tflz.dto.CreateProposeRequestDTO;
import cn.shiyang.tflz.dto.ShowProposeResponseDTO;
import cn.shiyang.tflz.entity.*;
import cn.shiyang.tflz.service.*;
import cn.shiyang.tflz.utils.UtilsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiyang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/tflz/propose")
@Api("用户倡议前端控制器")
@Validated
public class ProposeController {
    @Value("${custom.page.size}")
    private Integer PAGE_SIZE;

    @Autowired
    private ProposeService proposeService;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/createPropose")
    @ApiOperation("创建倡议")
    public Map createPropose(@RequestBody @Valid CreateProposeRequestDTO proposeRequestDTO,
                             HttpSession session) {
        Map<String, Object> map = new HashMap<>();

        // 检查验证码
        if (!utilsService.verifyCheckCode(map, proposeRequestDTO.getCheckCode(), (String) session.getAttribute("checkCode"))) {
            return map;
        }

        // 检查标签
        String tagsMain = proposeRequestDTO.getTags();
        tagsMain = tagsMain.trim();
        String[] tagsArray = tagsMain.split("\\s+");
        if (tagsMain == null || tagsMain.length() == 0 || tagsArray.length == 0) {
            map.put("isSucceed", false);
            map.put("msg", "标签不能为空！");
            return map;
        }

        // 检查是否重复发帖
        QueryWrapper<Propose> qw = new QueryWrapper<>();
        qw.eq("name", proposeRequestDTO.getName());
        if (proposeService.count(qw) > 0) {
            map.put("isSucceed", false);
            map.put("msg", "存在主题相同的倡议！请修改主题");
            return map;
        }

        // 创建逻辑(Propose、Tag、ProposeTag表)
        if (!proposeService.createPropose(proposeRequestDTO, tagsArray, map)) {
            return map;
        }

        map.put("isSucceed", true);
        return map;
    }

    @GetMapping("/getList/{pageNum}")
    @ApiOperation("获取倡议分页列表")
    public Map getList(@PathVariable("pageNum")
                       @Valid @Min(value = 1, message = "请求页数不能小于1")
                               Integer pageNum) {
        Map<String, Object> map = new HashMap<>();
        IPage<Propose> page = new Page<>(pageNum, PAGE_SIZE);
        QueryWrapper<Propose> qw_page = new QueryWrapper<>();
        qw_page.orderByDesc("time_reply");
        IPage<Propose> pageInfo = proposeService.page(page, qw_page);
        List<ShowProposeResponseDTO> li = new ArrayList<>(pageInfo.getRecords().size());

        for (Propose p : pageInfo.getRecords()) {
            ShowProposeResponseDTO sp = utilsService.proposeToResponseDTO(p, false);

            li.add(sp);
        }
        pageInfo.setRecords(null);
        map.put("pageInfo", pageInfo);
        map.put("records", li);
        return map;
    }

    @PostMapping("/getProposeDetails/{id}")
    @ApiOperation("获取倡议详情")
    public ShowProposeResponseDTO getProposeDetails(@PathVariable
                                                    @Valid @Min(value = 1, message = "倡议id不能小于1")
                                                            Integer id) {
        QueryWrapper<Propose> qw = new QueryWrapper<>();
        qw.eq("id", id);
        Propose p = proposeService.getOne(qw);

        if (p == null) {
            return null;
        }

        ShowProposeResponseDTO sp = utilsService.proposeToResponseDTO(p, true);

        return sp;
    }
}

