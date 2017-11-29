package net.toregard.jerseydemo.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

import javax.ws.rs.GET;

@Builder
@Getter
@Setter
public class B {
    String kunde;
    C c;
    public int tallet;

    private int kallMeg(){
        return 1;
    }
}
