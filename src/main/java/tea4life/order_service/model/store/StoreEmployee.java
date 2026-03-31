package tea4life.order_service.model.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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

import jakarta.persistence.Id;

@Entity
@Table(
        name = "store_employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_store_employee_store_keycloak", columnNames = {"store_id", "keycloak_id"})
        }
)
@SQLDelete(sql = "UPDATE store_employees SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StoreEmployee extends BaseEntity {

    @Id
    @SnowflakeGenerated
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "fk_store_employee_store"))
    Store store;

    @Column(name = "keycloak_id", nullable = false)
    String keycloakId;

    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;
}
