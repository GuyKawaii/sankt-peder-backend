package resturant.business.configuration;

import resturant.security.entity.Role;
import resturant.security.entity.UserWithRoles;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import resturant.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {



    UserWithRolesRepository userWithRolesRepository;
    String passwordUsedByAll;

    public SetupDevUsers(UserWithRolesRepository userWithRolesRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

    private void setupUserWithRoleUsers() {

        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }
}
