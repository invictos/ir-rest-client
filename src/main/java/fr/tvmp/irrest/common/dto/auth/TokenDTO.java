package fr.tvmp.irrest.common.dto.auth;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO implements CPAMDto {
    @NonNull private String token;
}