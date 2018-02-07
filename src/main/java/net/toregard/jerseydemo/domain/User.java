package net.toregard.jerseydemo.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "user")
@Builder
@Getter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlAttribute(name = "ssn")
    private String ssn;
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;

}