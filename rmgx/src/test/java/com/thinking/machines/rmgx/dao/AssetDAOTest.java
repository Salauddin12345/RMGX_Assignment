package com.thinking.machines.rmgx.dao;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import com.thinking.machines.rmgx.dao.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.exception.*;
import java.text.*;
import java.util.*;

@SpringBootTest
class AssetDAOTest
{

@Autowired
AssetDAO assetDAO;

@Test
void Testadd() 
{
try
{	
Asset a=new Asset();
a.setName("chair");

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
a.setPurchaseDate(sdf.parse("2021-05-12"));
a.setConditionNote("abc");
a.setAssignmentStatus("available");
Category c=new Category();
c.setId(1); // no need to fill full information in category 
a.setCategory(c);
assetDAO.add(a);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    

}


@Test
void Testupdate() 
{
try
{	
Asset a=new Asset();
a.setName("chair");

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
a.setPurchaseDate(sdf.parse("2021-05-12"));
a.setConditionNote("abc");
a.setAssignmentStatus("available");
Category c=new Category();
c.setId(1); // no need to fill full information in category 
a.setCategory(c);
assetDAO.update(a);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}

@Test
void TestGetAll() 
{
try
{	
List<Asset> list=assetDAO.getAll();
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}

@Test
@Disabled
void TestGetByName() 
{
try
{
String name="keyboard";	
Asset a=assetDAO.getByName(name);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}

@Test
@Disabled
void Testdelete() 
{
try
{
String assetName="keyboard";	
assetDAO.delete(assetName);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}

@Test
@Disabled
void TestAssign() 
{
try
{
String assetName="keyboard";	
int employeeId=2;
assetDAO.assign(assetName,employeeId);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}


@Test
@Disabled
void TestRevoke() 
{
try
{
String assetName="keyboard";	
int employeeId=1;
assetDAO.revoke(assetName,employeeId);
}catch(DAOException daoException)
{
Assertions.fail("failed with messsage : "+daoException.getMessage());
}
catch(Exception e)
{
Assertions.fail("you might have made a mistke while writing test case");
}    
}



}
