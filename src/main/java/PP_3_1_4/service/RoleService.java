package PP_3_1_4.service;

import PP_3_1_4.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    List<Role> getAllRoles();
}
