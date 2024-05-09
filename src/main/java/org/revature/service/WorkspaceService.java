package org.revature.service;

import org.revature.cloud.KubeClient;
import org.revature.dto.WorkspaceData;
import org.revature.entity.Workspace;
import org.revature.exception.WorkspaceException;
import org.revature.repository.WorkspaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service class for handling abstracted lab interactions.
 */
@Service
public class WorkspaceService {
    CMDService cmdService;
    WorkspaceRepository workspaceRepository;
    KubeClient kubeClient;

    @Autowired
    public WorkspaceService(CMDService cmdService, WorkspaceRepository workspaceRepository, KubeClient kubeClient){
        this.cmdService = cmdService;
        this.workspaceRepository = workspaceRepository;
        this.kubeClient = kubeClient;
    }
    public WorkspaceData createWorkspace(WorkspaceData requestedWorkspace) throws WorkspaceException {
        Workspace w = DTOToWorkspace(requestedWorkspace);
        w.setOpenDate(new Timestamp(System.currentTimeMillis()));
        w.setLastActivity(new Timestamp(System.currentTimeMillis()));
        w.setLastOverwrite(new Timestamp(System.currentTimeMillis()));
        w.setActive(true);
//        TODO: need a better naming scheme for pod name. this is a placeholder.
        w.setContainerName("ws"+Math.random()*Integer.MAX_VALUE);
        workspaceRepository.save(w);
        kubeClient.spinupPod(w);
//        TODO: Sidecar image must be pushed to dockerhub.
//        kubeClient.spinupSidecar(w);
//        TODO: Run exec in pod to load lab.
        kubeClient.spinupService(w);
        workspaceRepository.save(w);
        return workspaceToDTO(w);
    }
    public List<String> workspaceScan() throws WorkspaceException {
        return null;
    }

    public void labInit(String containerName) throws WorkspaceException {

    }

    public void labSave(String containerName) throws WorkspaceException {

    }

    public void deleteWorkspace(String container) throws WorkspaceException {

    }


    public WorkspaceData workspaceToDTO(Workspace w){
        WorkspaceData dto = new WorkspaceData();
        dto.setId(w.getId());
        dto.setLab(w.getLab());
        dto.setUserId(w.getUserId());
        dto.setOpenDate(w.getOpenDate());
        dto.setCloseDate(w.getCloseDate());
        dto.setLastOverwrite(w.getLastOverwrite());
        dto.setLastActivity(w.getLastActivity());
        dto.setActive(w.isActive());
        return dto;
    }
    public Workspace DTOToWorkspace(WorkspaceData dto){
        Workspace w = new Workspace();
        w.setId(dto.getId());
        w.setLab(dto.getLab());
        w.setUserId(dto.getUserId());
        w.setOpenDate(dto.getOpenDate());
        w.setCloseDate(dto.getCloseDate());
        w.setLastOverwrite(dto.getLastOverwrite());
        w.setLastActivity(dto.getLastActivity());
        w.setActive(dto.isActive());
        return w;
    }
}
