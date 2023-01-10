package sidepj.learningchain.domain.user;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sidepj.learningchain.domain.common.BaseEntity;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String loginEmail;

    private String name;

    private String nickName;
    @Convert(converter = UserPhaseConvertor.class)
    private UserPhase userPhase;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Builder
    public User(String nickName,String name){
        this.nickName = nickName;
        this.name = name;
    }
}
