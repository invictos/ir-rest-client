package fr.tvmp.irrest.common.dto.remboursement;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemboursementDTO implements CPAMDto {
    @NonNull UUID id;
    @NonNull UUID patient;
    @NonNull UUID medecin;
    @NonNull List<? extends ARemboursableDTO> remboursables;
}
