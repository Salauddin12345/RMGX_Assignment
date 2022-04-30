package com.thinking.machines.rmgx.dto;
import javax.persistence.*;
import java.util.*;

@Entity
public class Asset implements java.io.Serializable
{
@Id
private String name;
private Date purchaseDate;
private String conditionNote;
private String assignmentStatus;
@ManyToOne
private Category category;

public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setPurchaseDate(java.util.Date purchaseDate)
{
this.purchaseDate=purchaseDate;
}
public java.util.Date getPurchaseDate()
{
return this.purchaseDate;
}
public void setConditionNote(java.lang.String conditionNote)
{
this.conditionNote=conditionNote;
}
public java.lang.String getConditionNote()
{
return this.conditionNote;
}
public void setAssignmentStatus(java.lang.String assignmentStatus)
{
this.assignmentStatus=assignmentStatus;
}
public java.lang.String getAssignmentStatus()
{
return this.assignmentStatus;
}
public void setCategory(Category category)
{
this.category=category;
}
public Category getCategory()
{
return this.category;
}
}

