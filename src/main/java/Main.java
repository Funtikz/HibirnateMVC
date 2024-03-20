import mapper.CompanyMapper;
import mapper.UserMapper;
import repository.UserRepository;
import dto.UserDto;
import service.UserService;
import utilityOpen.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSession();) {
            Session session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                    (proxy, method, args1)
                            -> method.invoke(sessionFactory.getCurrentSession(), args1));

            session.beginTransaction();

            UserRepository repository = new UserRepository(session);
            CompanyMapper companyMapper = new CompanyMapper();
            UserMapper userMapper = new UserMapper(companyMapper);
            UserService service = new UserService(repository, userMapper);

            List<UserDto> all = service.findAll();
            System.out.println(all);




            session.getTransaction().commit();
        }
    }
}
