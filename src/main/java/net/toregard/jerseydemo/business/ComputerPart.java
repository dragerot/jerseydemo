package net.toregard.jerseydemo.business;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
