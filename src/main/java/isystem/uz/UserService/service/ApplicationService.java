package isystem.uz.UserService.service;
import isystem.uz.UserService.exception.BadRequestException;
import isystem.uz.UserService.model.Application;
import isystem.uz.UserService.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Component
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application create(Application app){
        app.setCreatedAt(LocalDateTime.now());
        app.setStatus(true);
        applicationRepository.save(app);
        return app;
    }

    public Application get(Integer id) throws RuntimeException {
        Optional<Application> getApplication = applicationRepository.findById(id);
        if(getApplication.isEmpty()){
            throw new BadRequestException("Application is not found");
        }
        return getApplication.get();
    }

    public Application update(Integer id, Application application){
        Application oldApplication = get(id);
        oldApplication.setUpdatedAt(LocalDateTime.now());
        oldApplication.setUserId(application.getUserId());
        oldApplication.setUser(application.getUser());
        oldApplication.setJobId(application.getJobId());
        oldApplication.setSalary(application.getSalary());
        oldApplication.setStartDate(application.getStartDate());
        oldApplication.setEndDate(application.getEndDate());
        applicationRepository.save(oldApplication);
        return oldApplication;
    }

    public Application delete(Integer id) {
        Application oldApplication = getEntity(id);
        applicationRepository.delete(oldApplication);
        return oldApplication;
    }

    private Application getEntity(Integer id) {
        Optional<Application> optional = applicationRepository.findById(id);
        if (optional.isEmpty()){
            throw new BadRequestException("Application not found");
        }
        return optional.get();
    }
}
