package isystem.uz.UserService.service;

import isystem.uz.UserService.exception.BadRequestException;
import isystem.uz.UserService.model.Job;
import isystem.uz.UserService.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public boolean create(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus(true);
        jobRepository.save(job);
        return true;
    }

    public Job get(Integer id) {
        return getEntity(id);
    }


    public Job update(Integer id, Job job) {
        Job oldJob = getEntity(id);
        oldJob.setTitle(job.getTitle());
        oldJob.setDescription(job.getDescription());
        oldJob.setCreatedAt(job.getCreatedAt());
        oldJob.setDeletedAt(job.getDeletedAt());
        oldJob.setStatus(job.getStatus());
        oldJob.setUpdatedAt(LocalDateTime.now());
        return jobRepository.save(job);
    }

    public Job delete(Integer id) {
        Job oldJob = getEntity(id);
        oldJob.setDeletedAt(LocalDateTime.now());
        jobRepository.save(oldJob);
        return oldJob;
    }

    private Job getEntity(Integer id) {
        Optional<Job> optional = jobRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Job not found");
        }
        return optional.get();
    }
}
