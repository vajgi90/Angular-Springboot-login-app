package hu.flowacademy.stockmarket.persistance.dto;

import hu.flowacademy.stockmarket.persistance.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegister {
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;
}
