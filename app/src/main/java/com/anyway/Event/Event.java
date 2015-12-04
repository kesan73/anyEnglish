package com.anyway.Event;

/**
 * Created by taekyong on 2015-09-28.
 */
public class Event {

    int mId;
    int mType;

    public Event() {
    }

    public Event(int mId, int mType) {
        this.mId = mId;
        this.mType = mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmId() {
        return mId;
    }

    public int getmType() {
        return mType;
    }


}
