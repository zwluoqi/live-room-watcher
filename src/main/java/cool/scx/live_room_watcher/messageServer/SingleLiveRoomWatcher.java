package cool.scx.live_room_watcher.messageServer;

import cool.scx.live_room_watcher.douyin.DouYinLiveRoomWatcher;

public class SingleLiveRoomWatcher {
    
    public DouYinLiveRoomWatcher liveRoomWatcher;
    public String liveId;
    public void Create(String liveId){
        this.liveId = liveId;
        liveRoomWatcher = new DouYinLiveRoomWatcher(String.format("https://live.douyin.com/%s", liveId));

        liveRoomWatcher.onChat(chat -> {
            System.out.println("[消息] " + chat.user().nickName() + " : " + chat.content());
            LiveData.addMessage(this.liveId,10,"message",chat.user().nickName().replace("\n", "").replace("\r", "").replace("*",""),chat.content());
        }).onUser(user -> {
            System.out.println("[来了] " + user.nickName());
            LiveData.addMessage(this.liveId,3,"enter", user.nickName().replace("\n", "").replace("\r", "").replace("*",""),"");
        }).onLike(like -> {
            System.out.println("[点赞] " + like.user().nickName() + " x " + like.count());
            LiveData.addMessage(this.liveId,20,"like", like.user().nickName().replace("\n", "").replace("\r", "").replace("*","")," x " + like.count());
        }).onFollow(follow -> {
            System.out.println("[关注] " + follow.user().nickName());
            LiveData.addMessage(this.liveId,2,"love",  follow.user().nickName().replace("\n", "").replace("\r", "").replace("*",""),"");
        }).onGift(gift -> {
            System.out.println("[礼物] " + gift.user().nickName() + " : " + gift.name() + " x " + gift.count());
            LiveData.addMessage(this.liveId,1,"gift_send",  gift.user().nickName().replace("\n", "").replace("\r", "").replace("*",""),gift.name() + " x " + gift.count());
        });

        liveRoomWatcher.startWatch();

        System.out.println("[直播流地址] " + liveRoomWatcher.liveRoomWebStreamURLs());
    }
}
