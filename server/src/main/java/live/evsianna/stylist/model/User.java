package live.evsianna.stylist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import live.evsianna.stylist.util.AuthorityDeserializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "app_user")
@Entity
@ToString(exclude = {"roles", "favors"})
@NoArgsConstructor
@EqualsAndHashCode(of = {"email", "firstName", "lastName"}, callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @Size(min = 2, max = 50, message = "Имя должно быть не меньше 2 и не больше 50 символов.")
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Поле имени не должно быть пустым.")
    private String firstName;

    @Size(min = 2, max = 50, message = "Фамилия должна быть не меньше 2 и не больше 50 символов.")
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Поле фамилии не должно быть пустым.")
    private String lastName;

    @Column(name = "age")
    @Positive(message = "Возраст должен быть больше нуля.")
    private int age;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Поле телефона не должно быть пустым.")
    private String phone;

    @Email(message = "Поле электронной почты должно быть корректным.")
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Поле электронной почты не должно быть пустым.")
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @CreationTimestamp
    private LocalDateTime updated;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Version
    @JsonIgnore
    private int version;

    @JoinTable(name = "user_role",
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})},
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @JoinTable(name = "user_favor",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "favor_id", referencedColumnName = "id"))
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Favor> favors = new HashSet<>();

    @Override
    @JsonDeserialize(using = AuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final HashSet<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName())));
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }

    @Builder
    private User(String firstName, String lastName, int age,
                 String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public void addFavor(@NotNull final Favor favor) {
        favor.incrementPopularity();
        favors.add(favor);
    }
}
