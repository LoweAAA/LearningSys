package learningsys.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class News {
    private int ganhuo_id;
    private String desc;
    private String who;
    private String url;

    @Id
    @Column(name = "ganhuo_id")
    public int getId() {
        return ganhuo_id;
    }

    public void setId(int ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
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

        if (ganhuo_id != news.ganhuo_id) return false;
        if (desc != null ? !desc.equals(news.desc) : news.desc != null) return false;
        if (who != null ? !who.equals(news.who) : news.who != null) return false;
        if (url != null ? !url.equals(news.url) : news.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ganhuo_id;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (who != null ? who.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
