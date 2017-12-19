package cn.nju.service.impl;

import cn.nju.dao.MessageRepository;
import cn.nju.dao.UserRepository;
import cn.nju.model.Message;
import cn.nju.model.User;
import cn.nju.service.MessageService;
import cn.nju.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MessageVO> findAllByPhotoId(String pid) {
        List<Message> messages = messageRepository.findAllByPidOrderByTime(pid);
        return wrapMessageToMessageVO(messages);
    }

    @Override
    public boolean addMessage(String senderId, String info, String pid) {
        Message message = new Message();
        message.setSenderId(senderId);
        message.setPid(pid);
        message.setContent(info);
        messageRepository.save(message);
        return true;
    }

    private List<MessageVO> wrapMessageToMessageVO(List<Message> messages){
        List<MessageVO> messageVOS = new ArrayList<>();
        if (messages!=null && !messages.isEmpty()){
            for (Message message : messages){
                MessageVO messageVO = wrapMessageToMessageVO(message);
                if (messageVO!=null){
                    messageVOS.add(messageVO);
                }
            }
        }
        return messageVOS;
    }

    private MessageVO wrapMessageToMessageVO(Message message){
        if (message!=null){
            MessageVO messageVO = new MessageVO(message);
            String senderId = messageVO.getSenderId();
            User sender = userRepository.findOne(senderId);
            messageVO.setSenderName(sender.getUname());
            return messageVO;
        }
        return null;
    }
}
