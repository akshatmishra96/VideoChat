package com.example.videochat.model;

import com.example.videochat.rtm.ChatManager;

import java.util.ArrayList;
import java.util.List;

import io.agora.rtm.RtmMessage;

public class MessageListBean {
    private String accountOther;
    private List<MessageBean> messageBeanList;

    public MessageListBean(String account, List<MessageBean> messageBeanList) {
        this.accountOther = account;
        this.messageBeanList = messageBeanList;
    }
    public MessageListBean(String account, ChatManager chatManager) {
        accountOther = account;
        messageBeanList = new ArrayList<>();

        List<RtmMessage> messageList = chatManager.getAllOfflineMessages(account);
        for (RtmMessage m : messageList) {

            MessageBean bean = new MessageBean(account, m.getText(), false);
            messageBeanList.add(bean);
        }
    }

    public String getAccountOther() {
        return accountOther;
    }

    public void setAccountOther(String accountOther) {
        this.accountOther = accountOther;
    }

    public List<MessageBean> getMessageBeanList() {
        return messageBeanList;
    }

    public void setMessageBeanList(List<MessageBean> messageBeanList) {
        this.messageBeanList = messageBeanList;
    }
}