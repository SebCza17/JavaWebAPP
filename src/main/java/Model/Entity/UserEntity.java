package Model.Entity;

public class UserEntity {
    private int id;
    private String username;
    private String email;
    private String password;
    private UserGroupEntity userGroupEntity;

    public UserEntity(Object user) {
    }

    public UserEntity(int id, String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserGroupEntity getUserGroupEntity() {
        return userGroupEntity;
    }

    public void setUserGroupEntity(UserGroupEntity userGroupEntity) {
        this.userGroupEntity = userGroupEntity;
    }
}
