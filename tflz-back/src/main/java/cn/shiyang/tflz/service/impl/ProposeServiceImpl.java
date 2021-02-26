package cn.shiyang.tflz.service.impl;

import cn.shiyang.tflz.dto.CreateProposeRequestDTO;
import cn.shiyang.tflz.entity.Propose;
import cn.shiyang.tflz.entity.ProposeTag;
import cn.shiyang.tflz.entity.Tag;
import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.mapper.ProposeMapper;
import cn.shiyang.tflz.service.ProposeService;
import cn.shiyang.tflz.service.ProposeTagService;
import cn.shiyang.tflz.service.TagService;
import cn.shiyang.tflz.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
@Service
public class ProposeServiceImpl extends ServiceImpl<ProposeMapper, Propose> implements ProposeService {
    @Autowired
    private ProposeService proposeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ProposeTagService proposeTagService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public boolean createPropose(CreateProposeRequestDTO proposeRequestDTO, String[] tagsArray, Map msgMap) {
        Propose propose = new Propose();

        propose.setUid(proposeRequestDTO.getUid());
        propose.setName(proposeRequestDTO.getName());
        propose.setDescription(proposeRequestDTO.getDescription());
        propose.setCntLike(0);
        Date date = new Date();
        propose.setTimeCreate(date);
        propose.setTimeReply(date);

        boolean flag = proposeService.save(propose);
        if (flag == false) {
            msgMap.put("isSucceed", false);
            msgMap.put("msg", "创建失败！请检查您填写的数据是否合法");
            return false;
        }

        QueryWrapper<Tag> qwt = new QueryWrapper<>();
        for (String s : tagsArray) {
            s = s.trim();
            if (s.length() == 0) {
                continue;
            }
            qwt.eq("name", s);
            int tid = -1;
            if (tagService.count(qwt) > 0) {
                tid = tagService.getOne(qwt).getId();
            } else {
                Tag nt = new Tag();
                nt.setName(s);
                tagService.save(nt);
                tid = nt.getId();
            }
            ProposeTag pt = new ProposeTag();
            pt.setSid(propose.getId());
            pt.setTid(tid);
            proposeTagService.save(pt);
            qwt.clear();
        }

        // 更新用户活跃状态
        UpdateWrapper<User> uwu = new UpdateWrapper<>();
        uwu.eq("id", proposeRequestDTO.getUid());
        uwu.set("time_modify", date);
        userService.update(uwu);

        return true;
    }
}
