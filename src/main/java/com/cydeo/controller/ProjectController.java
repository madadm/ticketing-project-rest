package com.cydeo.controller;


import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {


private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    ResponseEntity<ResponseWrapper> getProjects(){


        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectService.listAllProjects(), HttpStatus.OK));

    }

    @GetMapping("/{code}")
    ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code){

        return ResponseEntity.ok(new ResponseWrapper("Project is successfully retrieved", projectService.getByProjectCode(code), HttpStatus.OK));

    }
    @PostMapping
    ResponseEntity<ResponseWrapper> craeteProject(@RequestBody ProjectDTO projectDTO){

        projectService.save(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created", HttpStatus.CREATED));

    }
    @PutMapping
    ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO projectDTO){

        projectService.update(projectDTO);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", HttpStatus.OK));

    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("id") String id){

        projectService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));

    }

    @GetMapping("/manager/project-status")
    ResponseEntity<ResponseWrapper> getProjectByManager(){

        List<ProjectDTO> projectDTOList = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved", projectService.listAllProjects(), HttpStatus.OK));


    }

    @PutMapping("/manager/complete/{projectCode}")
    ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode") String projectCode){

        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully completed", projectService.listAllProjects(), HttpStatus.OK));


    }



}
