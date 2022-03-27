package fr.tvmp.irrest.common.dto.utilisateur;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPublicDTO implements CPAMDto {
    @NonNull private UUID id;
    @NonNull private String prenom;
    @NonNull private String nom;
}
