package com.inquistivecat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inquistivecat.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hp
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
