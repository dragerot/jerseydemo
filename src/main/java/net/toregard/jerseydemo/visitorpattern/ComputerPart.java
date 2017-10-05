package net.toregard.jerseydemo.visitorpattern;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
