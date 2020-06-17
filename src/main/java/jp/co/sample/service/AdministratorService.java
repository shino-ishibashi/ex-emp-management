package jp.co.sample.service;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository adminRepository;

    public Administrator findAdminByEmailAndPassword(String email, String password){
        return adminRepository.findAdminByEmailAndPassword(email, password);
    }

    /**
     *
     * @param admin DBに追加するAdministratorオブジェクト
     * @return DBに追加するオブジェクト内のEmailが存在していたらfalse,していなかったらtrueを返す
     */
    public boolean insertAdmin(Administrator admin){
        Administrator newAdmin = adminRepository.findByEmail(admin.getMailAddress());
        if(Objects.isNull(newAdmin)){
            return false;
        }else {
            adminRepository.insertAdmin(admin);
            return true;
        }
    }
}
