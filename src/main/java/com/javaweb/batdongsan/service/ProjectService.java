package com.javaweb.batdongsan.service;

import com.javaweb.batdongsan.enums.ProjectStatus;
import com.javaweb.batdongsan.model.request.project.ProjectRequest;
import com.javaweb.batdongsan.model.response.project.ProjectResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> getAllProjects();

    ProjectResponse createProject(ProjectRequest request);

    Long countTotalProjects();

    List<ProjectResponse> getProjectsAboutToExpire(int day);

    ProjectResponse updateProject(Long id,ProjectRequest request);

    Void deleteProject(Long id);

    List<ProjectResponse> getByStatus(ProjectStatus status);

    Integer countByStatus(ProjectStatus status);

    ProjectResponse updateProjectStatus(Long id);

    ProjectResponse rejectProject(Long id);

    ProjectResponse pauseProject(Long id);
}
