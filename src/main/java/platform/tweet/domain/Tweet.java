package platform.tweet.domain;

import platform.users.domain.UserEmail;
import platform.shared.domain.UserId;
import platform.users.domain.UserName;
import platform.users.domain.UserPicture;

import java.time.Instant;

public class Tweet {
    private TweetId id;
    private TweetContent content;
    private LikeCount likeCount;
    private ReplyCount replyCount;
    private UserId authorId;
    private UserName authorName;
    private UserEmail authorEmail;
    private UserPicture authorPicture;
    private TweetId parentTweetId;
    private Instant createdOn;

    public Tweet(TweetId id, TweetContent content, LikeCount likeCount, ReplyCount replyCount, UserId authorId, UserName authorName, UserEmail authorEmail, UserPicture authorPicture, TweetId parentTweetId, Instant createdOn) {
        this.id = id;
        this.content = content;
        this.likeCount = likeCount;
        this.replyCount = replyCount;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorPicture = authorPicture;
        this.parentTweetId = parentTweetId;
        this.createdOn = createdOn;
    }

    public void updateContent(TweetContent content) {
        this.content = content;
    }

    public boolean isReply() {
        return parentTweetId != null;
    }

    public TweetId id() {
        return id;
    }

    public TweetContent content() {
        return content;
    }

    public LikeCount likeCount() {
        return likeCount;
    }

    public ReplyCount replyCount() {
        return replyCount;
    }

    public UserId authorId() {
        return authorId;
    }

    public UserName authorName() {
        return authorName;
    }

    public TweetId parentTweetId() {
        return parentTweetId;
    }

    public Instant createdOn() {
        return createdOn;
    }

    public UserEmail authorEmail() {
        return authorEmail;
    }

    public UserPicture authorPicture() {
        return authorPicture;
    }
}
