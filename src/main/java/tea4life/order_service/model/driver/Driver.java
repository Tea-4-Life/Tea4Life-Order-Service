package tea4life.order_service.model.driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Entity
@Table(name = "drivers")
@SQLDelete(sql = "UPDATE drivers SET is_deleted = 1 WHERE id = ?")
@SQLRestriction("is_deleted = 0")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver extends BaseEntity {

    @Id
    @SnowflakeGenerated
    Long id;

    @Column(nullable = false, name = "keycloak_id", unique = true)
    String keycloakId;

    @Column(nullable = false, name = "full_name")
    String fullName;

    @Column(nullable = false)
    String phone;

    @Column(nullable = false, name = "is_deleted")
    boolean isDeleted = false;
}
