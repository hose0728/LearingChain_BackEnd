package sidepj.learningchain.domain.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @LastModifiedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
