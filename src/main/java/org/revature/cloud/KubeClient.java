package org.revature.cloud;

import com.azure.resourcemanager.AzureResourceManager;
import org.hibernate.jdbc.Work;
import org.revature.entity.Workspace;
import org.revature.exception.CMDException;
import org.revature.exception.WorkspaceException;
import org.revature.service.CMDService;
import org.revature.util.Cloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class to manage low-level interactions with pods.
 */
@Service
public class KubeClient {
    CMDService cmdService;
    AzureResourceManager azureResourceManager;
    @Autowired
    public KubeClient(CMDService cmdService, AzureResourceManager azureResourceManager){
        this.cmdService = cmdService;
        this.azureResourceManager = azureResourceManager;
    }

    /**
     * Begin the initial setup of just the workspace container.
     */
    public Workspace spinupPod(Workspace w) throws WorkspaceException {
        try{
            String[] cmd = new String[] {
                    "kubectl",
                    "run",
                    "pod",
                    w.getContainerName(),
                    "--image="+Cloud.workspaceImage,
                    "--labels=\"app=workspace\"",
                    "--port=8443"
            };
            String result = cmdService.runCommandReturnOutput(cmd);
            return w;
        }catch(CMDException e){
            throw new WorkspaceException("Issue spinning up pod on cloud", e);
        }
    }

    /**
     * Attach the container's auth proxy sidecar to the workspace pod.
     */
    public void spinupSidecar(Workspace w) throws WorkspaceException {
        try{
            String[] cmd = new String[] {
                    "kubectl",
                    "patch",
                    "deploy",
                    w.getContainerName(),
                    "--patch",
                    "'{\"spec\":",
                    "{\"template\":",
                    "{\"spec\":",
                    "{\"containers\":",
                    "[{\"name\":",
                    "\""+w.getContainerName()+"-sidecar\"",
                    "\"image\":",
                    "\""+Cloud.sidecarImage+"\"}]}}}}'"
            };
            String result = cmdService.runCommandReturnOutput(cmd);
        }catch(CMDException e){
            throw new WorkspaceException("Issue spinning up pod on cloud", e);
        }
    }

    /**
     * TODO: Get ALL pods as described by Kubectl.
     * We will use Kubectl's word instead of our own database to avoid
     * orphaned workspaces.
     */
    public List<String> getAllPods(Workspace w){
        return null;
    }

    /**
     * TODO: Get ALL services as described by Kubectl.
     */
    public List<String> getAllServices(Workspace w){
        return null;
    }

    /**
     * Expose the workspace's sidecar proxy to other services inside the cluster
     * by creating a clusterIP for the sidecar.
     */
    public Workspace spinupService(Workspace w) throws WorkspaceException {
        try{
            String[] cmd = new String[] {
                    "kubectl",
                    "expose",
                    "pod",
                    w.getContainerName(),
                    "--labels=\"port="+w.getPort()+"\"",
                    "--target=8444"
            };
            String result = cmdService.runCommandReturnOutput(cmd);
            return w;
        }catch(CMDException e){
            throw new WorkspaceException("Issue spinning up pod on cloud", e);
        }
    }

    /**
     * TODO Ask kubectl to eliminate a workspace pod.
     */
    public Workspace spindownPod(Workspace w){
        return null;
    }

    /**
     * TODO Ask kubectl to eliminate a service associate with a pod.
     */
    public Workspace spindownService(Workspace s){
        return null;
    }
}
