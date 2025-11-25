package com.javaweb.batdongsan.api;

import com.javaweb.batdongsan.entity.Project;
import com.javaweb.batdongsan.enums.ProjectStatus;
import com.javaweb.batdongsan.model.request.project.ProjectRequest;
import com.javaweb.batdongsan.model.response.ApiResponse;
import com.javaweb.batdongsan.model.response.project.ProjectResponse;
import com.javaweb.batdongsan.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("")
    public ApiResponse<ProjectResponse> createProject(@RequestBody ProjectRequest request){
        ApiResponse<ProjectResponse> response = new ApiResponse<>();
        response.setMessage("Project created successfully");
        response.setResult(projectService.createProject(request));
        return response;
    }

    @GetMapping("")
    public ApiResponse<List<ProjectResponse>> getAllProjects(){
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>();
        response.setResult(projectService.getAllProjects());
        return response;
    }
    @GetMapping("/about-to-expire")
    public ApiResponse<List<ProjectResponse>> getProjectsAboutToExpire(
            @RequestParam(value = "day", defaultValue = "30") int day)
    {
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>();
        response.setResult(projectService.getProjectsAboutToExpire(day));
        return response;
    }

    @GetMapping("/countTotal")
    public ApiResponse<Long> countTotalProjects(){
        ApiResponse<Long> response = new ApiResponse<>();
        response.setResult(projectService.countTotalProjects());
        return response;
    }
    @GetMapping("/admin/approval")
    public ApiResponse<List<ProjectResponse>> getByStatus(@RequestParam (name = "status") ProjectStatus status){
        ApiResponse<List<ProjectResponse>> response = new ApiResponse<>();
        response.setResult(projectService.getByStatus(status));
        return  response;
    }
    @GetMapping("/admin/approval/counts")
    public ApiResponse<String> countByStatus(@RequestParam (name = "status") ProjectStatus status){
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("co " + projectService.countByStatus(status) + " du an: " + status);
        return  response;
    }

    @PutMapping("/{id}")
    public ApiResponse<ProjectResponse> updateProject(@PathVariable Long id,@RequestBody ProjectRequest request){
        ApiResponse<ProjectResponse> response = new ApiResponse<>();
        response.setMessage("Project updated successfully");
        response.setResult(projectService.updateProject(id, request));
        return response;
    }
    @PutMapping("/admin/approve/{id}")
    public ApiResponse<ProjectResponse> approveProject(@PathVariable Long id){
        ApiResponse<ProjectResponse> response = new ApiResponse<>();
        response.setMessage("Phê duyệt thành công! ");
        response.setResult(projectService.updateProjectStatus(id));
        return response;
    }
    @PutMapping("/admin/reject/{id}")
    public ApiResponse<ProjectResponse> rejectProject(@PathVariable Long id){
        ApiResponse<ProjectResponse> response = new ApiResponse<>();
        response.setMessage("Từ chối thành công! ");
        response.setResult(projectService.rejectProject(id));
        return response;
    }
    @PutMapping("/admin/pause/{id}")
    public ApiResponse<ProjectResponse> pauseProject(@PathVariable Long id){
        ApiResponse<ProjectResponse> response = new ApiResponse<>();
        response.setMessage("Hoãn dự án thành công! ");
        response.setResult(projectService.pauseProject(id));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProject(@PathVariable Long id){
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Project deleted successfully");
        response.setResult(projectService.deleteProject(id));
        return response;
    }

}
