package fr.tvmp.irrest.client.procedures.choice;

import fr.tvmp.irrest.common.dto.CPAMDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ChoiceProxy implements Choice{
    private String displayName;
    private Object underlyingObject;

    public <T> T getUnderlyingObject(Class<T> tClass){
        try{
            return tClass.cast(underlyingObject);
        }catch (ClassCastException ignored) {
            throw new RuntimeException("Code is bad");
        }
    }

    @Override
    public String getPickerName() {
        return this.getDisplayName();
    }

    static public <T extends CPAMDto> ChoiceProxy buildFromDTO(T dto, Function<T,String> mapper){
        return new ChoiceProxy(mapper.apply(dto), dto);
    }

    static public <T extends CPAMDto> List<Choice> buildFromDTOList(Collection<T> dto, Function<T,String> mapper){
        return dto.stream()
                .map(d -> buildFromDTO(d, mapper))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
