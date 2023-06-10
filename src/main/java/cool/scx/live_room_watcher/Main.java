package cool.scx.live_room_watcher;

import cool.scx.live_room_watcher.douyin.DouYinLiveRoomWatcher;
import cool.scx.live_room_watcher.messageServer.LiveData;

public class Main {

    public static void main(String[] args) {

        ApplicationWeb applicationWeb = null;
        try {
            applicationWeb = new ApplicationWeb();
            applicationWeb.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
