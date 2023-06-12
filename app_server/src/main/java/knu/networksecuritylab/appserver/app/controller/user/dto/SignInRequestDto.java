package knu.networksecuritylab.appserver.app.controller.user.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Setter
public class SignInRequestDto {

    @Column(unique = true)
    @NotBlank(message = "학번은 비어있을 수 없습니다.")
    private String studentId;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상, 20자 이하입니다..")
    private String password;
}
