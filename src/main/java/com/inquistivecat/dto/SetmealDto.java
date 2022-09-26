package com.inquistivecat.dto;


import com.inquistivecat.entity.Setmeal;
import com.inquistivecat.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @author hp
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
