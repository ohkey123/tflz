package cn.shiyang.tflz.service;

import cn.shiyang.tflz.dto.CreateReplyRequestDTO;
import cn.shiyang.tflz.entity.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shiyang
 * @since 2021-01-26
 */
public interface ReplyService extends IService<Reply> {
    boolean createReply(CreateReplyRequestDTO dto);
}
