package ark.clanner.juststudent.entity.view;

/**
 * Created by Clanner on 2018/5/3.
 */
public class Button {
    private String name;//菜单标题

    private String type;//菜单的响应动作类型

    private Button[] sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
