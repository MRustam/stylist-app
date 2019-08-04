package live.evsianna.stylist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import live.evsianna.stylist.model.projection.UserProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import live.evsianna.stylist.utils.CustomAuthorityDeserializer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@Table(name = "user_tab")
@Entity
@JsonSerialize
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "email"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails, UserProjection {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;


    @Size(min = 2, max = 50, message = "Имя должно быть не меньше 2 и не больше 50 символов.")
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Поле имени не должно быть пустым.")
    private String firstName;

    @Size(min = 2, max = 50, message = "Фамилия должна быть не меньше 2 и не больше 50 символов.")
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Поле фамилии не должно быть пустым.")
    private String lastName;

    @Positive(message = "Возраст должен быть больше нуля.")
    @Column(name = "age")
    private int age;

    @Column(name = "phone", nullable = false)
    @NotBlank(message = "Поле телефона не должно быть пустым.")
    private String phone;

    @Email(message = "Поле электронной почты должно быть корректным.")
    @Column(name = "email", nullable = false)
    @NotBlank(message = "Поле электронной почты не должно быть пустым.")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMMM-yyyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMMM-yyyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime updated;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Version
    private int version;

    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("SIMPLE_USER"));
    }

    @Override
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
        return true;
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
}
