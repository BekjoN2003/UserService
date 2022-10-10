package isystem.uz.UserService.repository;

import isystem.uz.UserService.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUserId(Integer userId);
    @Query("from Application where userId = :userId and startDate < : date and endDate > :date")
    List<Application> salaryByMont(@Param("userId") Integer id,
                                   @Param("date") LocalDate date);

}
