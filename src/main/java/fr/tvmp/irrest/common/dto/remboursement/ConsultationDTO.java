package fr.tvmp.irrest.common.dto.remboursement;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConsultationDTO extends ARemboursableDTO{
    @NonNull private UUID medecin;

    public ConsultationDTO(@NonNull Float prix, @NonNull Float tauxCPAM, @NonNull Float remboursement, @NonNull UUID medecin) {
        super(prix, tauxCPAM, remboursement);
        this.medecin = medecin;
    }
}
