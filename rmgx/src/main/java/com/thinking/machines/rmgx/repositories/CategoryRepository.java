package com.thinking.machines.rmgx.repositories;
import org.springframework.data.jpa.repository.*;
import com.thinking.machines.rmgx.dto.*;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{
public Category findByName(String name);
}