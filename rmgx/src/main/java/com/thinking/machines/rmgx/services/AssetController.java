package com.thinking.machines.rmgx.services;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.dao.*;
import com.thinking.machines.rmgx.exception.*;

import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping(value="/asset")
public class AssetController
{
@Autowired
private AssetDAO assetDAO;

@PostMapping("/add")
public ActionResponse add(@RequestBody Asset asset)
{
ActionResponse actionResponse=new ActionResponse();
try
{
Asset c=assetDAO.add(asset);
actionResponse.success=true;
actionResponse.result=c;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}


@PostMapping("/update")
public ActionResponse update(@RequestBody Asset asset)
{
ActionResponse actionResponse=new ActionResponse();
try
{
assetDAO.update(asset);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}

@PostMapping("/delete/{name}")
public ActionResponse delete(@PathVariable("name") String assetName)
{
ActionResponse actionResponse=new ActionResponse();
try
{
assetDAO.delete(assetName);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}



@GetMapping("/getAll")
public ActionResponse getAll()
{
ActionResponse actionResponse=new ActionResponse();
try
{
actionResponse.result=assetDAO.getAll();
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}

@GetMapping("/getByName")
public ActionResponse getByName(@RequestParam("name") String name)
{
ActionResponse actionResponse=new ActionResponse();
try
{
actionResponse.result=assetDAO.getByName(name);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}

@PostMapping("/assign/{name}/{id}")
public ActionResponse assign(@PathVariable("name") String assetName,@PathVariable("id") int employeeId)
{
ActionResponse actionResponse=new ActionResponse();
try
{
assetDAO.assign(assetName,employeeId);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}

@PostMapping("/revoke/{name}/{id}")
public ActionResponse revoke(@PathVariable("name") String assetName,@PathVariable("id") int employeeId)
{
ActionResponse actionResponse=new ActionResponse();
try
{
assetDAO.revoke(assetName,employeeId);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}


}