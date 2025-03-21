package org.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.auth.entity.UserInfo;


@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name ="Test_Kafka")
public class UserInfoDto extends UserInfo {

    @Column(name = "user_name_test")
    private String userName;

    @Column(name = "last_name_test")
    private String lastName;

    @Column(name = "phone_number_test")
    private Long phoneNumber;

    @Column(name = "email_test")
    private String email;
}
