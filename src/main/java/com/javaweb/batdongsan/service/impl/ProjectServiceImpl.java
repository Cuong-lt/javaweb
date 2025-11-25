package com.javaweb.batdongsan.service.impl;

import com.javaweb.batdongsan.converter.ProjectConverter;
import com.javaweb.batdongsan.entity.Project;
import com.javaweb.batdongsan.enums.ProjectStatus;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.project.ProjectRequest;
import com.javaweb.batdongsan.model.response.project.ProjectResponse;
import com.javaweb.batdongsan.repository.ProjectRepository;
import com.javaweb.batdongsan.service.ActivityLogService;
import com.javaweb.batdongsan.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {

     ProjectRepository projectRepository;
     ProjectConverter projectConverter;
     ActivityLogService activityLogService;

    @Override
    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResponse> projectResponses = projects.stream()
                .map(project -> projectConverter.toResponse(project))
                .toList();
        return projectResponses;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
       Project project =  projectConverter.toEntity(request);
       projectRepository.save(project);
       activityLogService.log("CREATE ","ADMIN",
               "Tạo dự án: " + project.getName(),"Project");
        return projectConverter.toResponse(project);
    }

    @Override
    public Long countTotalProjects() {
        Long total = projectRepository.totalProjects();
        return total;
    }

    @Override
    public List<ProjectResponse> getProjectsAboutToExpire(int day) {
        LocalDate today = LocalDate.now();
        LocalDate upcomingDate = today.plusDays(day);
        List<Project> projects = projectRepository.findProjectsAboutToExpire(today,upcomingDate);
        List<ProjectResponse> projectResponses = projects.stream()
                .map(project -> projectConverter.toResponse(project))
                .toList();

        return projectResponses;
    }

    @Override
    public ProjectResponse updateProject(Long id,ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        project = projectConverter.toEntity(request);
        projectRepository.save(project);
        activityLogService.log("UPDATE ","ADMIN",
                "Cập nhật dự án: " + project.getName(),"Project");
        return projectConverter.toResponse(project);
    }

    @Override
    public Void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        projectRepository.deleteById(id);
        activityLogService.log("DELETE ","ADMIN",
                "Xóa dự án: " + project.getName(),"Project");
        return null;
    }

    @Override
    public List<ProjectResponse> getByStatus(ProjectStatus status) {
        List<Project> projectList = projectRepository.findByStatus(status)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        List<ProjectResponse> projectResponses = projectList.stream()
                .map(project -> projectConverter.toResponse(project))
                .toList();
        return projectResponses;
    }

    @Override
    public Integer countByStatus(ProjectStatus status) {
        List<Project> projectList = projectRepository.findByStatus(status)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        return projectRepository.countByStatus(status);
    }

    @Override
    public ProjectResponse updateProjectStatus(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        project.setStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);
        return projectConverter.toResponse(project);
    }

    @Override
    public ProjectResponse rejectProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        project.setStatus(ProjectStatus.REJECTED);
        projectRepository.save(project);
        return projectConverter.toResponse(project);
    }

    @Override
    public ProjectResponse pauseProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
        project.setStatus(ProjectStatus.PAUSED);
        projectRepository.save(project);
        return projectConverter.toResponse(project);
    }
}
