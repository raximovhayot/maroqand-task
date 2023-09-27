package uz.raximov.maroqandtask.mapper;

import org.mapstruct.Mapper;
import uz.raximov.maroqandtask.domain.auth.User;
import uz.raximov.maroqandtask.payload.auth.AccountDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AccountDTO toAccountDTO(User user);
}
