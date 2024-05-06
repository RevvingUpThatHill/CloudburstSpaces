package org.revature.controller;

import org.revature.dto.WorkspaceData;
import org.revature.exception.WorkspaceException;
import org.revature.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WorkspaceController {
    WorkspaceService workspaceService;
    @Autowired
    public WorkspaceController(WorkspaceService workspaceService){
        this.workspaceService = workspaceService;
    }

    @PostMapping("/workspace")
    public ResponseEntity<WorkspaceData> postWorkspace(@RequestBody WorkspaceData workspaceData){
        try{
            WorkspaceData result = workspaceService.createWorkspace(workspaceData);
            return ResponseEntity.ok()
                    .body(result);
        } catch (WorkspaceException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/workspace/{id}")
    public ResponseEntity<WorkspaceData> getWorkspaceId(@PathVariable String id){
        return null;
    }
    @DeleteMapping("/workspace/{name}")
    public ResponseEntity<WorkspaceData> deleteWorkspace(@PathVariable String name){
        try {
            workspaceService.deleteWorkspace(name);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (WorkspaceException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
