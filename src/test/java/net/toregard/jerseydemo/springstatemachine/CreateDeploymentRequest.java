package net.toregard.jerseydemo.springstatemachine;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CreateDeploymentRequest {
    private String id;
}
