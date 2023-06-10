package cool.scx.live_room_watcher.messageServer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class LiveData {
    private static Map<String, Queue<Message>> liveData = new HashMap<>();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void addMessage(String liveId, int order, String command, String sender, String message) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = currentDateTime.format(formatter);

        Message messageDict = new Message();
        messageDict.setCommand(command);
        messageDict.setSender(sender);
        messageDict.setMessage(message);
        messageDict.setSendTime(currentTime);
        messageDict.setDateTime(currentDateTime.toEpochSecond(ZoneOffset.UTC));
        messageDict.setOrder(order);

        Queue<Message> messageQueue = liveData.get(liveId);
        if (messageQueue == null) {
            messageQueue = new ConcurrentLinkedQueue<>();
            liveData.put(liveId, messageQueue);
        }

        messageQueue.add(messageDict);
    }

    public static String popMessages(String liveId, String authorization) {
        
        List<Message> messages = new ArrayList<>();
        
        if (liveId == null || liveId.isEmpty()) {

        }else {
            Queue<Message> messageQueue = liveData.get(liveId);
            int count = 10;
            if (messageQueue != null) {
                while (!messageQueue.isEmpty() && count > 0) {
                    Message message = messageQueue.poll();
                    messages.add(message);
                    count--;
                }
            } else {
                messageQueue = new ConcurrentLinkedQueue<>();
                liveData.put(liveId, messageQueue);
                SingleLiveRoomWatcher singleLiveRoomWatcher = new SingleLiveRoomWatcher();
                singleLiveRoomWatcher.Create(liveId);
//            createLive(liveId, authorization);
                //TODO
            }
        }

        Map<String, List<Message>> messageDict = new HashMap<>();
        messageDict.put("messages", messages);

        String jsonMessages = "";
        try {
            jsonMessages = objectMapper.writeValueAsString(messageDict);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonMessages;
    }

//
//    public static void main(String[] args) {
//        addMessage("liveId1", 1, "command1", "sender1", "message1");
//    }
}
