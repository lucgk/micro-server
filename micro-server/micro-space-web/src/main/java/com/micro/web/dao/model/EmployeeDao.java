package com.micro.web.dao.model;

import com.micro.web.common.config.excel.IExcel;
import com.micro.web.dao.MyBatisCrudRepository;
import com.micro.web.entity.excel.EmployeeExcel;
import com.micro.web.entity.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 如未使用   @MapperScan  ，则@Mapper 必配
 */
public interface EmployeeDao extends MyBatisCrudRepository {

    void insertEmployee(Employee ee);

    void updateEmployee(Employee ee);

    List<IExcel> getExcelDatas(EmployeeExcel sdtcExcel);

}
