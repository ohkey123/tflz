package cn.shiyang.tflz.utils;

import cn.shiyang.tflz.dto.ShowNoticeResponseDTO;
import cn.shiyang.tflz.dto.ShowProposeResponseDTO;
import cn.shiyang.tflz.dto.ShowReplyResponseDTO;
import cn.shiyang.tflz.dto.UserLoginSucceedResponseDTO;
import cn.shiyang.tflz.entity.*;
import cn.shiyang.tflz.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UtilsService {
    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ProposeTagService proposeTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private ProposeService proposeService;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean verifyCheckCode(Map msgMap, String input, String token) {
        if (input == null || input.trim().length() == 0) {
            msgMap.put("isSucceed", false);
            msgMap.put("msg", "验证码不能为空！");
            return false;
        }

        if (token == null) {
            msgMap.put("isSucceed", false);
            msgMap.put("msg", "服务器错误！");
            return false;
        }

        if (token.toLowerCase().compareTo(input.toLowerCase()) != 0) {
            msgMap.put("isSucceed", false);
            msgMap.put("msg", "验证码错误！");
            return false;
        }

        return true;
    }

    public UserLoginSucceedResponseDTO findUserThenToResponseDTO(String column, String value, boolean isDetailMode) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (column.compareTo("id") == 0) {
            qw.eq(column, Integer.parseInt(value));
        } else {
            qw.eq(column, value);
        }
        User user_t = userService.getOne(qw);

        UserLoginSucceedResponseDTO dto = new UserLoginSucceedResponseDTO();
        dto.setId(user_t.getId());
        dto.setEmail(user_t.getEmail());
        dto.setName(user_t.getName());
        dto.setAge(user_t.getAge());
        dto.setSex(user_t.getSex());
        dto.setDeclaration(user_t.getDeclaration());
        dto.setTimeCreate(format.format(user_t.getTimeCreate()));
        dto.setTimeModify(format.format(user_t.getTimeModify()));

        if (isDetailMode) {
            QueryWrapper<Notice> qwn = new QueryWrapper<>();
            qwn.eq("uid", user_t.getId());
            qwn.eq("isUnread", true);
            dto.setNotice(noticeService.count(qwn));
        }

        return dto;
    }

    public ShowProposeResponseDTO proposeToResponseDTO(Propose p, boolean isDetailMode) {
        ShowProposeResponseDTO sp = new ShowProposeResponseDTO();
        sp.setId(p.getId());
        sp.setName(p.getName());
        sp.setDescription(p.getDescription());
        sp.setCntLike(p.getCntLike());
        sp.setTimeCreate(format.format(p.getTimeCreate()));
        sp.setTimeReply(format.format(p.getTimeReply()));

        UserLoginSucceedResponseDTO dto = this.findUserThenToResponseDTO("id", String.valueOf(p.getUid()), false);

        sp.setBuilder(dto);

        List<Tag> lit = new ArrayList<>();

        QueryWrapper<ProposeTag> qwpt = new QueryWrapper<>();
        QueryWrapper<Tag> qwt = new QueryWrapper<>();

        qwpt.eq("sid", p.getId());
        List<ProposeTag> pts = proposeTagService.list(qwpt);
        for (ProposeTag pt : pts) {
            qwt.eq("id", pt.getTid());
            lit.add(tagService.getOne(qwt));
            qwt.clear();
        }
        qwpt.clear();
        sp.setTags(lit);

        if (isDetailMode) {
            QueryWrapper<Reply> qwr = new QueryWrapper<>();
            qwr.eq("sid", p.getId());
            List<Reply> replies = replyService.list(qwr);
            List<ShowReplyResponseDTO> lir = new ArrayList<>(replies.size());

            for (Reply r : replies) {
                ShowReplyResponseDTO sr = new ShowReplyResponseDTO();
                sr.setId(r.getId());
                sr.setSid(r.getSid());
                sr.setDetail(r.getDetail());
                sr.setTimeCreate(format.format(r.getTimeCreate()));

                UserLoginSucceedResponseDTO su = this.findUserThenToResponseDTO("id", String.valueOf(r.getUid()), false);

                sr.setUser(su);
                lir.add(sr);
            }

            sp.setReplies(lir);
        }

        return sp;
    }

    public List getNoticeResponseDTOList(Integer uid, boolean isUnread) {
        QueryWrapper<Notice> qwn = new QueryWrapper<>();
        qwn.eq("uid", uid);
        qwn.orderByDesc("time_create");
        qwn.eq("isUnread", isUnread);
        List<Notice> notices = noticeService.list(qwn);
        List<ShowNoticeResponseDTO> ret = new ArrayList<>(notices.size());

        QueryWrapper<Propose> qwp = new QueryWrapper<>();
        QueryWrapper<User> qwu = new QueryWrapper<>();

        for (Notice n : notices) {
            ShowNoticeResponseDTO dto = new ShowNoticeResponseDTO();
            dto.setId(n.getId());
            dto.setIsUnread(n.getIsUnread());
            dto.setTimeCreate(format.format(n.getTimeCreate()));

            qwp.eq("id", n.getSid());
            dto.setPid(n.getSid());
            dto.setPname(proposeService.getOne(qwp).getName());
            qwp.clear();

            qwu.eq("id", n.getSuid());
            dto.setSid(n.getSuid());
            dto.setSname(userService.getOne(qwu).getName());
            qwu.clear();

            ret.add(dto);
        }

        return ret;
    }
}
