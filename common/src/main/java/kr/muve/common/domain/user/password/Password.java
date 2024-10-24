package kr.muve.common.domain.user.password;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Embeddable;
import kr.muve.common.security.Encrypts;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Getter
@JsonSerialize(using = PasswordSerializer.class)
@JsonDeserialize(using = PasswordDeserializer.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {
    private String value;

    public Password(String value) {
        changePassword(value);
    }

    private void changePassword(String value) {
        this.value = Encrypts.sha256Encrypt(value);
    }

    public void setValue(String value) {
        changePassword(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
