package com.thinking.machines.rmgx.services;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.dao.*;
import com.thinking.machines.rmgx.exception.*;

import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController
{
@Autowired
private EmployeeDAO employeeDAO;

@PostMapping("/add")
public ActionResponse add(@RequestBody Employee employee)
{
ActionResponse actionResponse=new ActionResponse();
try
{
Employee c=employeeDAO.add(employee);
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
public ActionResponse update(@RequestBody Employee employee)
{
ActionResponse actionResponse=new ActionResponse();
try
{
employeeDAO.update(employee);
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
actionResponse.result=employeeDAO.getAll();
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}


@PostMapping("/delete/{id}")
public ActionResponse delete(@PathVariable("id") int employeeId)
{
ActionResponse actionResponse=new ActionResponse();
try
{
employeeDAO.delete(employeeId);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
}
return actionResponse;
}

}