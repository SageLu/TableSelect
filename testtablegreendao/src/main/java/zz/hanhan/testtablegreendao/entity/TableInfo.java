package zz.hanhan.testtablegreendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2017/1/15.
 */
@Entity
public class TableInfo {
    @Id
    private long id;
    private String newsChannelName;
    private String newsChannelId;
    private String newsChannelType;
    private boolean newsChannelSelect;
    private int newsChannelIndex;
    private Boolean newsChannelFixed;
    @Generated(hash = 6070102)
    public TableInfo(long id, String newsChannelName, String newsChannelId,
            String newsChannelType, boolean newsChannelSelect, int newsChannelIndex,
            Boolean newsChannelFixed) {
        this.id = id;
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }
    @Generated(hash = 1659288981)
    public TableInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNewsChannelName() {
        return this.newsChannelName;
    }
    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }
    public String getNewsChannelId() {
        return this.newsChannelId;
    }
    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }
    public String getNewsChannelType() {
        return this.newsChannelType;
    }
    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }
    public boolean getNewsChannelSelect() {
        return this.newsChannelSelect;
    }
    public void setNewsChannelSelect(boolean newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }
    public int getNewsChannelIndex() {
        return this.newsChannelIndex;
    }
    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }
    public Boolean getNewsChannelFixed() {
        return this.newsChannelFixed;
    }
    public void setNewsChannelFixed(Boolean newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }
    
}
