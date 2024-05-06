package org.revature.chron;

import org.revature.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TODO: Chron jobs related to the maintenance of workspace pods,
 * such as autosaves, activity checks, and terminations.
 */
@Component
public class WorkspaceChron {
    WorkspaceService workspaceService;
    @Autowired
    public WorkspaceChron(WorkspaceService workspaceService){
        this.workspaceService = workspaceService;
    }

    /**
     * TODO: eliminate pods that are scheduled to be deleted.
     */
    @Scheduled(fixedRate = 5000)
    public void cleanup(){

    }
}
