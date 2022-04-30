package com.thinking.machines.rmgx.dto;
import javax.persistence.*;

@Entity
public class AssetEmployee implements java.io.Serializable
{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int recordId;
private String assetName;
private int employeeId;

public void setRecordId(int recordId)
{
this.recordId=recordId;
}
public int getRecordId()
{
return this.recordId;
}
public void setAssetName(java.lang.String assetName)
{
this.assetName=assetName;
}
public java.lang.String getAssetName()
{
return this.assetName;
}
public void setEmployeeId(int employeeId)
{
this.employeeId=employeeId;
}
public int getEmployeeId()
{
return this.employeeId;
}
}