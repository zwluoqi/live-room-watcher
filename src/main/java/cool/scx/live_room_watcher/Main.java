package cool.scx.live_room_watcher;

import cool.scx.live_room_watcher.douyin.DouYinLiveRoomWatcher;
import cool.scx.live_room_watcher.messageServer.LiveData;
import cool.scx.live_room_watcher.messageServer.SingleLiveRoomWatcher;

public class Main {

    public static void main(String[] args) {

        ApplicationWeb applicationWeb = null;
        try {
            applicationWeb = new ApplicationWeb();
            applicationWeb.start();
            //test
            SingleLiveRoomWatcher singleLiveRoomWatcher = new SingleLiveRoomWatcher();
            singleLiveRoomWatcher.Create("196714286243");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
