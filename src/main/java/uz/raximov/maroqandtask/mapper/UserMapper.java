package uz.raximov.maroqandtask.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.payload.auth.AccountDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "username", target = "username")
    AccountDTO toAccountDTO(User user);
}
