package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CategoryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAOImpl {
    public boolean insertCategory(CategoryDto categoryDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Product_Category values(?,?)",
                categoryDto.getCategoryId(),
                categoryDto.getCategoryName()

        );
    }

    public boolean updateCategory(CategoryDto categoryDto) throws SQLException {
        return SQLUtil.execute(
                "Update Product_Category set Cat_Name=? where Cat_Id=?",
                categoryDto.getCategoryName(),
                categoryDto.getCategoryId()
        );
    }

    public boolean deleteCategory(CategoryDto categoryDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Product_Category where Cat_Id=?",
                categoryDto.getCategoryId()
        );
    }

    public ArrayList<CategoryDto> loadTblCat() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product_Category");

        ArrayList<CategoryDto> categoryDtos = new ArrayList<>();

        while (rst.next()) {
            CategoryDto categoryDto = new CategoryDto(
                    rst.getString(1),
                    rst.getString(2)
            );
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }
}

