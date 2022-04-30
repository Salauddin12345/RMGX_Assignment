package com.thinking.machines.rmgx.repositories;
import org.springframework.data.jpa.repository.*;
import com.thinking.machines.rmgx.dto.*;
import java.util.*;

public interface AssetEmployeeRepository extends JpaRepository<AssetEmployee,Integer>
{
public AssetEmployee findByAssetName(String assetName);
public AssetEmployee findByAssetNameAndEmployeeId(String assetName,int employeeId);
}