package jp.co.sample.service;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository adminRepository;

    public Administrator findAdminByEmailAndPassword(String email, String password){
        return adminRepository.findAdminByEmailAndPassword(email, password);
    }

}
