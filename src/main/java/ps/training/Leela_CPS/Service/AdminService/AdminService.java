package ps.training.Leela_CPS.Service.AdminService;

import ps.training.Leela_CPS.Model.Admin;
import ps.training.Leela_CPS.Model.Course;
import ps.training.Leela_CPS.Model.Student;

import java.util.List;

public interface AdminService {
    public void addAdmin(Admin admin);
    public List<Admin> getAllAdmin();
    public Admin getAdminById(long id);
}
