
package com.thinking.machines.rmgx.dao;
import org.springframework.stereotype.*;
import com.thinking.machines.rmgx.repositories.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.exception.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;


@Service
public class EmployeeDAO
{
@Autowired
private EmployeeRepository employeeRepository;

public Employee add(Employee employee) throws DAOException
{
Employee c=null;
try
{
if(employee.getId()!=0) throw new DAOException("you are not supposed to set id of employee");
c=employeeRepository.save(employee);
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return c;
}

public void update(Employee employee) throws DAOException
{
try
{
Optional<Employee> optional=employeeRepository.findById(employee.getId());
if(optional.isPresent()==false) throw new DAOException("invalid employee id "+employee.getId());
employeeRepository.save(employee);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public List<Employee> getAll() throws DAOException
{
try
{
Iterable<Employee> itr=employeeRepository.findAll();
List<Employee> list=new LinkedList<>();
itr.forEach((t)->{
list.add(t);
});
return list;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
} 

public void delete(int id) throws DAOException
{
try
{
Optional<Employee> optional=employeeRepository.findById(id);
if(optional.isPresent()==false) throw new DAOException("invalid employee id "+id);
employeeRepository.deleteById(id);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}


}