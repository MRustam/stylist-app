package live.evsianna.stylist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Table(name = "order_tab")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "message"})
public class Order {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Size(min = 5, max = 200, message = "Название услуги должно быть не меньше 5 и не больше 100 символов.")
    @Column(name = "title", nullable = false)
    @NotBlank(message = "Поле названия услуги не должно быть пустым.")
    private String title;

    @Size(min = 5, max = 2000, message = "Сообщение должно быть не меньше 5 и не больше 2000 символов.")
    @Column(name = "message", nullable = false, length = 2000)
    @NotBlank(message = "Поле сообщения не должно быть пустым.")
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    @Builder
    private Order(String title, String message, User user) {
        this.title = title;
        this.message = message;
        this.user = user;
    }
}
