package kr.muve.common.domain.user;

import jakarta.persistence.*;
import kr.muve.common.domain.user.password.Password;
import kr.muve.common.service.user.UserJoinCommand;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false))
    @Embedded
    private Password password;

    private String phoneNumber;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "zipcode"))
    })
    private Address address;
    
    protected UserJpaEntity() {}

    private UserJpaEntity(String name, String email, Password password, String phoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static UserJpaEntity createUser(UserJoinCommand dto) {
        Address address = new Address(dto.getCity(), dto.getStreet(), dto.getZipcode());
        Password password = new Password(dto.getPassword());
        return new UserJpaEntity(dto.getName(), dto.getEmail(), password, dto.getPhoneNumber(), address);
    }

    public boolean validatePassword(String password) {
        return this.password.equals(new Password(password));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserJpaEntity that = (UserJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
