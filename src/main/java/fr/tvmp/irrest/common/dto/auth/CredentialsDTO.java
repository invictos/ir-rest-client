package fr.tvmp.irrest.common.dto.auth;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsDTO implements CPAMDto {
    @NonNull private UUID uuid;
    @NonNull private String password;
}