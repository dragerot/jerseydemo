package net.toregard.jerseydemo.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;

@Builder
@Getter
@Setter
public class A {

    private String navn;
    @Delegate(types = B.class)
    B b;
}
