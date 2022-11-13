package tn.esprit.spring.service;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.repository.RoleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
