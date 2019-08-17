package live.evsianna.stylist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "favor_tab")
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"title", "description"})
public class Favor {

    @Id
    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @Size(min = 4, max = 200, message = "Название услуги должно быть не меньше 4 и не больше 100 символов.")
    @Column(name = "title", nullable = false)
    @NotBlank(message = "Поле названия услуги не должно быть пустым.")
    private String title;

    @Size(max = 2000, message = "Сообщение должно быть не больше 2000 символов.")
    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Setter(AccessLevel.NONE)
    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "updated")
    @CreationTimestamp
    private LocalDateTime updated;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Version
    @JsonIgnore
    private int version;

    @Builder
    public Favor(String title,
                 String description,
                 BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public void incrementPopularity() {
        popularity++;
    }
}
