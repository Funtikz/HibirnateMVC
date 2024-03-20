package mapper;

import entity.Company;
import dto.CompanyDto;

public class CompanyMapper implements Mapper<Company, CompanyDto> {
    @Override
    public CompanyDto mapFrom(Company object) {
        return  new CompanyDto(
                object.getName()
        );
    }
}
