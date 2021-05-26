package com.example.videochat.rtm;

import android.content.Context;

import com.example.videochat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmMessage;
import io.agora.rtm.SendMessageOptions;


public class ChatManager {

    private Context mContext;
    private RtmClient mRtmclient;
    private List<RtmClientListener> mListenerList = new ArrayList<>();
    private SendMessageOptions mSendMsgOptions;
    private RtmMessagePool mMessagePool = new RtmMessagePool();


    public ChatManager(Context context) {
        mContext = context;
    }

    public void init() {
        String appID = "d0272eb3f6634389aefc9d2e9b102edf";

        try {
            mRtmclient = RtmClient.createInstance(mContext,"d0272eb3f6634389aefc9d2e9b102edf", new RtmClientListener() {
                @Override
                public void onConnectionStateChanged(int state, int reason) {
                    for (RtmClientListener listener : mListenerList) {
                        listener.onConnectionStateChanged(state, reason);
                    }
                }

                @Override
                public void onMessageReceived(RtmMessage rtmMessage, String peerId) {
                    if (mListenerList.isEmpty()) {

                        mMessagePool.insertOfflineMessage(rtmMessage, peerId);
                    } else {
                        for (RtmClientListener listener : mListenerList) {
                            listener.onMessageReceived(rtmMessage, peerId);
                        }
                    }
                }

                @Override
                public void onTokenExpired() {

                }

                @Override
                public void onPeersOnlineStatusChanged(Map<String, Integer> map) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mSendMsgOptions = new SendMessageOptions();
    }

    public RtmClient getRtmClient() {
        return mRtmclient;
    }

    public void unregisterListener(RtmClientListener listener) {
        mListenerList.remove(listener);
    }

    public SendMessageOptions getSendMessageOptions() {
        return mSendMsgOptions;
    }

    public void enableOfflineMessage(boolean enabled) {
        mSendMsgOptions.enableOfflineMessaging = enabled;
    }

    public List<RtmMessage> getAllOfflineMessages(String peerId) {
        return mMessagePool.getAllOfflineMessages(peerId);
    }

    public void removeAllOfflineMessages(String peerId) {
        mMessagePool.removeAllOfflineMessages(peerId);
    }

    public void registerListener(RtmClientListener listener) {
        mListenerList.add(listener);
    }
}