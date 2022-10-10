package isystem.uz.UserService.repository;

import isystem.uz.UserService.model.Job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    Optional<Job> findByIdAndDeletedAtIsNull(Integer id);
}
