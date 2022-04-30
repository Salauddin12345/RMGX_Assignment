package com.thinking.machines.rmgx.services;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.dao.*;
import com.thinking.machines.rmgx.exception.*;

import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping(value="/category")
public class CategoryController
{
@Autowired
private CategoryDAO categoryDAO;

@PostMapping("/add")
public ActionResponse add(@RequestBody Category category)
{
ActionResponse actionResponse=new ActionResponse();
try
{
Category c=categoryDAO.add(category);
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
public ActionResponse update(@RequestBody Category category)
{
ActionResponse actionResponse=new ActionResponse();
try
{
categoryDAO.update(category);
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
actionResponse.result=categoryDAO.getAll();
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}

@PostMapping("/delete/{id}")
public ActionResponse delete(@PathVariable("id") int categoryId)
{
ActionResponse actionResponse=new ActionResponse();
try
{
categoryDAO.delete(categoryId);
actionResponse.success=true;
}catch(DAOException daoException)
{
actionResponse.exception=daoException.getMessage();
return actionResponse;
}
return actionResponse;
}


}