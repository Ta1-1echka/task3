package entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanya on 16.10.2016.
 */
@XmlType(propOrder = { "user" }, name = "users")
@XmlRootElement
public class Users {
    private List<User> user;

    public Users(){
        user = new ArrayList<>();
    }

    public Users(List<User> users) {
        this.user = users;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> users) {
        this.user = users;
    }
}
