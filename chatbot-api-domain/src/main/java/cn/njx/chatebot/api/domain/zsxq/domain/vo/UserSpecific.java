package cn.njx.chatebot.api.domain.zsxq.domain.vo;

/**
 * @author njx
 * @description
 * @date 2023年4月5日19:40:50
 */
public class UserSpecific {

    private boolean liked;

    private boolean subscribed;

    public void setLiked(boolean liked){
        this.liked = liked;
    }
    public boolean getLiked(){
        return this.liked;
    }
    public void setSubscribed(boolean subscribed){
        this.subscribed = subscribed;
    }
    public boolean getSubscribed(){
        return this.subscribed;
    }

}
