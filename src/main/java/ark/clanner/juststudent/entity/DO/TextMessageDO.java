package ark.clanner.juststudent.entity.DO;

import javax.persistence.*;

/**
 * Created by Clanner on 2018/5/5.
 */
@Entity
@Table(name = "text_message", schema = "just_student")
public class TextMessageDO {
    private int id;
    private String fromUsername;
    private Integer createTime;
    private String content;

    public TextMessageDO() {
    }

    public TextMessageDO(String fromUsername, String content) {
        this.fromUsername = fromUsername;
        this.content = content;
    }

    public TextMessageDO(String fromUsername, Integer createTime, String content) {
        this.fromUsername = fromUsername;
        this.createTime = createTime;
        this.content = content;
    }

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

    public void setFromUsername(String fromusername) {
        this.fromUsername = fromusername;
    }

    @Basic
    @Column(name = "create_time")
    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createtime) {
        this.createTime = createtime;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        TextMessageDO that = (TextMessageDO) object;

        if (id != that.id) return false;
        if (fromUsername != null ? !fromUsername.equals(that.fromUsername) : that.fromUsername != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fromUsername != null ? fromUsername.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
