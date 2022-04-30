package com.thinking.machines.rmgx.dao;
import org.springframework.stereotype.*;
import com.thinking.machines.rmgx.repositories.*;
import com.thinking.machines.rmgx.dto.*;
import com.thinking.machines.rmgx.exception.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;


@Service
public class CategoryDAO
{
@Autowired
private CategoryRepository categoryRepository;

public Category add(Category category) throws DAOException
{
Category c=null;
try
{
if(category.getId()!=0) throw new DAOException("you are not supposed to set id of category");
c=categoryRepository.save(category);
}
catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return c;
}

public void update(Category category) throws DAOException
{
try
{
Optional<Category> optional=categoryRepository.findById(category.getId());
if(optional.isPresent()==false) throw new DAOException("invalid category id "+category.getId());
Category c=categoryRepository.findByName(category.getName());
if(c!=null && c.getId()!=category.getId()) throw new DAOException("category name already exists "+category.getName());
categoryRepository.save(category);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

public List<Category> getAll() throws DAOException
{
try
{
Iterable<Category> itr=categoryRepository.findAll();
List<Category> list=new LinkedList<>();
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
Optional<Category> optional=categoryRepository.findById(id);
if(optional.isPresent()==false) throw new DAOException("invalid category id "+id);
categoryRepository.deleteById(id);
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}

}