package learningsys.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class News {
    private int ganhuoId;
    private String desc;
    private String who;
    private String url;
    private String type;
    private String readability;
    private Timestamp publishedAt;

    @Id
    @Column(name = "ganhuo_id")
    public int getGanhuoId() {
        return ganhuoId;
    }

    public void setGanhuoId(int ganhuoId) {
        this.ganhuoId = ganhuoId;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "who")
    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (ganhuoId != news.ganhuoId) return false;
        if (desc != null ? !desc.equals(news.desc) : news.desc != null) return false;
        if (who != null ? !who.equals(news.who) : news.who != null) return false;
        if (url != null ? !url.equals(news.url) : news.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ganhuoId;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (who != null ? who.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "readability")
    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    @Basic
    @Column(name = "published_at")
    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }
}
