package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.entity.Employee;
import com.inquistivecat.mapper.EmployeeMapper;
import com.inquistivecat.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author hp
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
