package com.thinking.machines.rmgx.dao;
import org.springframework.stereotype.*;
import com.thinking.machines.rmgx.repositories.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.exception.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;


@Service
public class AssetDAO
{
@Autowired
private AssetRepository assetRepository;
@Autowired
private CategoryRepository categoryRepository;
@Autowired
private EmployeeRepository employeeRepository;
@Autowired
private AssetEmployeeRepository assetEmployeeRepository;


public Asset add(Asset asset) throws DAOException
{
Asset a=null;
try
{
Optional<Category> optional=categoryRepository.findById(asset.getCategory().getId());
if(optional.isPresent()==false) throw new DAOException("invalid Category in Asset");
// if assignment status is any other than mentined in question then it will be cosidered as invalid
// while adding i must be available
asset.setAssignmentStatus("available");
a=assetRepository.save(asset);
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return a;
}

public void update(Asset asset) throws DAOException
{
try
{
Optional<Asset> optional=assetRepository.findById(asset.getName());
if(optional.isPresent()==false) throw new DAOException("invalid asset name "+asset.getName());
Asset a=optional.get();
Optional<Category> opt=categoryRepository.findById(asset.getCategory().getId());
if(opt.isPresent()==false) throw new DAOException("invalid Category in Asset");
//here one can not change assignment status there will be seprate methods
//so assigning same value 
asset.setAssignmentStatus(a.getAssignmentStatus());
assetRepository.save(asset);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}


public void delete(String assetName) throws DAOException
{
try
{
Optional<Asset> optional=assetRepository.findById(assetName);
if(optional.isPresent()==false) throw new DAOException("invalid asset name "+assetName);

AssetEmployee  ae=assetEmployeeRepository.findByAssetName(assetName);
if(ae!=null) throw new DAOException("can't delete asset, it is alloted to an employee");
assetRepository.deleteById(assetName);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}


public List<Asset> getAll() throws DAOException
{
try
{
Iterable<Asset> itr=assetRepository.findAll();
List<Asset> list=new LinkedList<>();
itr.forEach((t)->{
list.add(t);
});
return list;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
} 

public Asset getByName(String name) throws DAOException
{
try
{
Asset a=assetRepository.findByName(name);
if(a==null) throw new DAOException("invalid name");
return a;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
} 

public void assign(String assetName,int employeeId) throws DAOException
{
try
{
Optional<Asset> optional=assetRepository.findById(assetName);
if(optional.isPresent()==false) throw new DAOException("invalid asset name "+assetName);
Asset a=optional.get();
Optional<Employee> opt=employeeRepository.findById(employeeId);
if(opt.isPresent()==false) throw new DAOException("invalid employee id "+employeeId);
AssetEmployee ae=assetEmployeeRepository.findByAssetName(assetName);
if(ae!=null && ae.getAssetName().equals(assetName)) throw new DAOException("this asset has already been alloted to employee");
if(ae!=null) throw new DAOException("this asset ("+assetName+") has been alloted to employee with employee Id : "+employeeId);

ae=new AssetEmployee();
ae.setAssetName(assetName);
ae.setEmployeeId(employeeId);
assetEmployeeRepository.save(ae);
a.setAssignmentStatus("assigned");
assetRepository.save(a);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public void revoke(String assetName,int employeeId) throws DAOException 
{
try
{
AssetEmployee ae=assetEmployeeRepository.findByAssetNameAndEmployeeId(assetName,employeeId);
if(ae==null) throw new DAOException("invalid assetName or employee id");
assetEmployeeRepository.deleteById(ae.getRecordId());
Asset a=assetRepository.getById(assetName);
a.setAssignmentStatus("Recovered");
assetRepository.save(a);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

}