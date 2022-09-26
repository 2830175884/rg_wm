package com.inquistivecat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inquistivecat.entity.AddressBook;
import com.inquistivecat.mapper.AddressBookMapper;
import com.inquistivecat.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author hp
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
