package fr.tvmp.irrest.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Adresse {
    @NonNull private String rue;
    @NonNull private String ville;
}
