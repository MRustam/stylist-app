package live.evsianna.stylist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author Rustam Mamedov
 */

@Table(name = "role")
@Entity
@NoArgsConstructor
public class Role {

    @Id
    @Column
    @Getter
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @Getter
    private String name;

    @Version
    private int version;

    public Role(String name) {
        this.name = name;
    }
}
