package cn.shiyang.tflz.service.impl;

import cn.shiyang.tflz.dto.CreateReplyRequestDTO;
import cn.shiyang.tflz.entity.Notice;
import cn.shiyang.tflz.entity.Propose;
import cn.shiyang.tflz.entity.Reply;
import cn.shiyang.tflz.entity.User;
import cn.shiyang.tflz.mapper.ReplyMapper;
import cn.shiyang.tflz.service.NoticeService;
import cn.shiyang.tflz.service.ProposeService;
import cn.shiyang.tflz.service.ReplyService;
import cn.shiyang.tflz.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProposeService proposeService;

    @Autowired
    private NoticeService noticeService;

    @Override
    @Transactional
    public boolean createReply(CreateReplyRequestDTO dto) {

        // 添加回复
        Reply reply = new Reply();
        reply.setSid(dto.getSid());
        reply.setUid(dto.getUid());
        reply.setDetail(dto.getDetail());
        Date date = new Date();
        reply.setTimeCreate(date);
        replyService.save(reply);

        // 更新账户活跃时间
        UpdateWrapper<User> uwu = new UpdateWrapper<>();
        uwu.eq("id", dto.getUid()).set("time_modify", date);
        userService.update(uwu);

        // 更新话题回复时间
        UpdateWrapper<Propose> uwp = new UpdateWrapper<>();
        uwp.eq("id", dto.getSid()).set("time_reply", date);
        proposeService.update(uwp);

        // 通知发起者
        Notice notice = new Notice();
        notice.setIsUnread(true);
        notice.setSid(dto.getSid());
        notice.setSuid(dto.getUid());

        QueryWrapper<Propose> qwp = new QueryWrapper<>();
        qwp.eq("id",dto.getSid());
        Propose p = proposeService.getOne(qwp);
        notice.setUid(p.getUid());
        notice.setTimeCreate(date);

        noticeService.save(notice);

        return true;
    }
}
