package mapper;

import entity.User;
import lombok.AllArgsConstructor;
import dto.UserDto;
@AllArgsConstructor
public class UserMapper  implements Mapper<User, UserDto> {
    private final CompanyMapper companyMapper;
    @Override
    public UserDto mapFrom(User object) {
        return new UserDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getSalary(),
                companyMapper.mapFrom(object.getCompany())

        );
    }
}
