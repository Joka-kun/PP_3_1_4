package PP_3_1_4.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
   // @NotEmpty(message = "Имя пользователя не может быть пустым")
   // @Size(min = 2, max = 30, message = "Имя должно состоять от 2 до 30 символов")
    private String name;

    @Column(name = "last_name")
  //  @NotEmpty(message = "Фамилия пользователя не может быть пустой")
  //  @Size(min = 2, max = 60, message = "Фамилия должна состоять от 2 до 60 символов")
    private String lastName;

    @Column(name = "age")
  //  @Min(value = 0, message = "Возраст не может быть меньше 0")
    private int age;

    @Column(name = "username")
   // @NotEmpty(message = "Username пользователя не может быть пустой")
    private String username;

    @Column(name = "password")
  //  @NotEmpty(message = "Password пользователя не может быть пустой")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_role", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String lastName, int age) {
        this.name = username;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getRolesName() {
        String ADMIN = "";
        String USER = "";
        for (Role role : roles) {
            if (role.getRole().equals("ROLE_ADMIN"))
            ADMIN = "ADMIN ";
            if (role.getRole().equals("ROLE_USER"))
            USER = "USER ";
        }
        return ADMIN + USER;
    }

    public String getRolesName(Role role) {
        String ADMIN = "";
        String USER = "";
            if (role.getRole().equals("ROLE_ADMIN"))
                ADMIN = "ADMIN ";
            if (role.getRole().equals("ROLE_USER"))
                USER = "USER ";
        return ADMIN + USER;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
