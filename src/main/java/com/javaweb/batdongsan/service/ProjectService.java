package com.javaweb.batdongsan.service;

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
}
