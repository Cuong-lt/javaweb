package com.javaweb.batdongsan.converter;

import com.javaweb.batdongsan.entity.Project;
import com.javaweb.batdongsan.entity.User;
import com.javaweb.batdongsan.enums.ProjectStatus;
import com.javaweb.batdongsan.exception.AppException;
import com.javaweb.batdongsan.exception.ErrorCode;
import com.javaweb.batdongsan.model.request.project.ProjectRequest;
import com.javaweb.batdongsan.model.response.project.ProjectResponse;
import com.javaweb.batdongsan.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public ProjectResponse toResponse(Project project){
        User user = userRepository.findByName(project.getUser().getName())
                .orElseThrow( () -> new AppException(ErrorCode.USER_NOT_FOUND));
        ProjectResponse response = modelMapper.map(project, ProjectResponse.class);
        response.setUserName(user.getName());
        return response;
    }

    public Project toEntity(ProjectRequest request) {
        Project project = modelMapper.map(request, Project.class);
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow( () -> new AppException(ErrorCode.USER_NOT_FOUND));
        project.setUser(user);
        ProjectStatus status = ProjectStatus.valueOf(request.getStatus());
        project.setStatus(status);
        return project;
    }
}
