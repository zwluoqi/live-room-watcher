package cool.scx.live_room_watcher;

import io.vertx.core.Vertx;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * <p>Abstract LiveRoomWatcher class.</p>
 *
 * @author scx567888
 * @version 0.0.1
 */
public abstract class AbstractLiveRoomWatcher implements LiveRoomWatcher {

    /**
     * Constant <code>vertx</code>
     */
    public static final Vertx vertx;
    
    /**
     * 默认情况下我们什么都不做
     */
    public static Consumer<Chat> DEFAULT_CHAT_HANDLER = chat -> {};
    
    /**
     * Constant <code>DEFAULT_USER_HANDLER</code>
     */
    public static Consumer<User> DEFAULT_USER_HANDLER = user -> {};
    
    /**
     * Constant <code>DEFAULT_LIKE_HANDLER</code>
     */
    public static Consumer<Like> DEFAULT_LIKE_HANDLER = like -> {};
    
    /**
     * Constant <code>DEFAULT_FOLLOW_HANDLER</code>
     */
    public static Consumer<Follow> DEFAULT_FOLLOW_HANDLER = follow -> {};
    
    /**
     * Constant <code>DEFAULT_GIFT_HANDLER</code>
     */
    public static Consumer<Gift> DEFAULT_GIFT_HANDLER = gift -> {};

    public static Consumer<String> DEFAULT_LIVE_STATE_HANDLER = gift -> {};

    static {
        vertx = Vertx.vertx();
    }

    private Consumer<Chat> chatHandler;
    private Consumer<User> userHandler;
    private Consumer<Like> likeHandler;
    private Consumer<Follow> followHandler;
    private Consumer<Gift> giftHandler;
    private Consumer<String> liveStateHandler;

    /**
     * <p>Constructor for AbstractLiveRoomWatcher.</p>
     */
    protected AbstractLiveRoomWatcher() {
        chatHandler = DEFAULT_CHAT_HANDLER;
        userHandler = DEFAULT_USER_HANDLER;
        likeHandler = DEFAULT_LIKE_HANDLER;
        followHandler = DEFAULT_FOLLOW_HANDLER;
        giftHandler = DEFAULT_GIFT_HANDLER;
        liveStateHandler = DEFAULT_LIVE_STATE_HANDLER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LiveRoomWatcher chatHandler(Consumer<Chat> handler) {
        Objects.requireNonNull(handler);
        this.chatHandler = handler;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LiveRoomWatcher userHandler(Consumer<User> handler) {
        Objects.requireNonNull(handler);
        this.userHandler = handler;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LiveRoomWatcher likeHandler(Consumer<Like> handler) {
        Objects.requireNonNull(handler);
        this.likeHandler = handler;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LiveRoomWatcher followHandler(Consumer<Follow> handler) {
        Objects.requireNonNull(handler);
        this.followHandler = handler;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LiveRoomWatcher giftHandler(Consumer<Gift> handler) {
        Objects.requireNonNull(handler);
        this.giftHandler = handler;
        return this;
    }
    
    @Override
    public LiveRoomWatcher liveStateHandler(Consumer<String> handler) {
        Objects.requireNonNull(handler);
        this.liveStateHandler = handler;
        return this;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Consumer<Chat> chatHandler() {
        return chatHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consumer<User> userHandler() {
        return userHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consumer<Like> likeHandler() {
        return likeHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consumer<Follow> followHandler() {
        return followHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Consumer<Gift> giftHandler() {
        return giftHandler;
    }

    @Override
    public Consumer<String> liveStateHandler() {
        return liveStateHandler;
    }
    
}
