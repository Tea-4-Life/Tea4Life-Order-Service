package tea4life.order_service.model.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import tea4life.order_service.config.database.SnowflakeGenerated;
import tea4life.order_service.model.base.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "stores")
@SQLDelete(sql = "UPDATE stores SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Store extends BaseEntity {

    @Id
    @SnowflakeGenerated
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    Double latitude;

    @Column(nullable = false)
    Double longitude;

    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;

    @OneToMany(mappedBy = "store")
    Set<StoreEmployee> storeEmployees = new LinkedHashSet<>();
}
