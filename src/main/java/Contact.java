import java.util.ArrayList;
import java.util.UUID;

public class Contact {
    private final UUID id;
    private String name;
    private String phoneNum;
    private String email;

    public Contact(String name, String phoneNum, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public Contact(UUID id, String name, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                "," + phoneNum +
                "," + email + "\n";
    }
}
