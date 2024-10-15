package kr.jinsang.sshop.domain.user;

import jakarta.persistence.*;
import kr.jinsang.sshop.service.user.UserJoinDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter
public class User {

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
    
    protected User() {}

    private User(String name, String email, String password, String phoneNumber, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static User createUser(UserJoinDto dto) {
        Address address = new Address(dto.getCity(), dto.getStreet(), dto.getZipcode());
        return new User(dto.getName(), dto.getEmail(), dto.getPassword(), dto.getPhoneNumber(), address);
    }
}
