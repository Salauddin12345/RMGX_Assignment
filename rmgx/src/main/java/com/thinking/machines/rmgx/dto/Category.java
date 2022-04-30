package com.thinking.machines.rmgx.dto;
import javax.persistence.*;

@Entity
public class Category implements java.io.Serializable
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(nullable=false,unique=true)
private String name;
private String description;

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
public void setDescription(java.lang.String description)
{
this.description=description;
}
public java.lang.String getDescription()
{
return this.description;
}
}