package fr.tvmp.irrest.common.dto.traitement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonneeMedicaleDTO implements CPAMDto {
    @NonNull UUID id;
    @NonNull DonneeMedicaleType type;
    @NonNull String value;
}