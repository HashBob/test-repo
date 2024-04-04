package ps.training.Leela_CPS.Service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ps.training.Leela_CPS.Model.Admin;
import ps.training.Leela_CPS.Repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;
    @Override
    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }
    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }
    @Override
    public Admin getAdminById(long id) {
        Optional<Admin> optionalStudent = adminRepository.findById(id);
        return optionalStudent.orElse(null);
    }
}
