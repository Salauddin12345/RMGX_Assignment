package com.thinking.machines.rmgx.dto;
import javax.persistence.*;

@Entity
public class Employee implements java.io.Serializable
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String name;
private String designation;

public void setId(int id)
{
this.id=id;
}
public int getId()
{
return this.id;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setDesignation(java.lang.String designation)
{
this.designation=designation;
}
public java.lang.String getDesignation()
{
return this.designation;
}
}