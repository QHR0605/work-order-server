package com.server.workordersystem.dto;

import com.server.workordersystem.entity.WorkOrder;

import java.util.List;

public class WorkOrderWithFiles {

    private WorkOrder workOrder;
    private List<String> commitImages;
    private List<String> solutionImages;

    public WorkOrderWithFiles(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }

    public List<String> getCommitImages() {
        return commitImages;
    }

    public void setCommitImages(List<String> commitImages) {
        this.commitImages = commitImages;
    }

    public List<String> getSolutionImages() {
        return solutionImages;
    }

    public void setSolutionImages(List<String> solutionImages) {
        this.solutionImages = solutionImages;
    }
}
