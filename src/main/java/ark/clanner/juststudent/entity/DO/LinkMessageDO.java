package ark.clanner.juststudent.entity.DO;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Clanner on 2018/5/5.
 */
@Entity
@Table(name = "link_message", schema = "just_student")
public class LinkMessageDO {
    private int id;
    private String fromUsername;
    private Integer createTime;
    private String title;
    private String description;
    private String url;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "from_username")
    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    @Basic
    @Column(name = "create_time")
    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        LinkMessageDO that = (LinkMessageDO) object;

        if (id != that.id) return false;
        if (createTime != that.createTime) return false;
        if (fromUsername != null ? !fromUsername.equals(that.fromUsername) : that.fromUsername != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fromUsername != null ? fromUsername.hashCode() : 0);
        result = 31 * result + createTime;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
