package kr.muve.common.domain.user;

import jakarta.persistence.*;
import kr.muve.common.service.user.UserJoinDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Getter
public class UserJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Embedded
    private Address address;
    
    protected UserJpaEntity() {}

    private UserJpaEntity(String name, String email, String password, String phoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static UserJpaEntity createUser(UserJoinDto dto) {
        Address address = new Address(dto.getCity(), dto.getStreet(), dto.getZipcode());
        return new UserJpaEntity(dto.getName(), dto.getEmail(), dto.getPassword(), dto.getPhoneNumber(), address);
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
