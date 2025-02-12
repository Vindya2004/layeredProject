package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.CustomerDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.ProductDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.ProductBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.ProductDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.SupplierDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Customer;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PRODUCT);
    SupplierDAO supplierDao = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean insert(ProductDto customerDto) throws SQLException {
        return productDAO.insert(new Product(customerDto.getTxtProId(), customerDto.getTxtProName(), customerDto.getTxtPrice(), customerDto.getTxtQty(), customerDto.getTxtInventory(), customerDto.getTxtSupplierId()));
    }

    @Override
    public boolean update(ProductDto customerDto) throws SQLException {
        return productDAO.update(new Product(customerDto.getTxtProId(), customerDto.getTxtProName(), customerDto.getTxtPrice(), customerDto.getTxtQty(), customerDto.getTxtInventory(), customerDto.getTxtSupplierId()));
    }

    @Override
    public boolean delete(Product customerDto) throws SQLException {
        return productDAO.delete(customerDto);
    }

    @Override
    public ArrayList<ProductDto> loadTbl() throws SQLException {
        ArrayList<Product> load = productDAO.loadTbl();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product : load) {
            productDtos.add(new ProductDto(product.getTxtProId(),product.getTxtProName(), (int) product.getTxtPrice(),product.getTxtQty(),product.getTxtInventory(), product.getTxtSupplierId()));
        }
        return productDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return productDAO.loadNExtID();
    }

    @Override
    public Double getpriceValues(String id) throws SQLException {
        return productDAO.getpriceValues(id);
    }

    @Override
    public int getCount(String productId) throws SQLException {
        return productDAO.getCount(productId);
    }

    @Override
    public List<String> getAllProductId() throws SQLException {
        return productDAO.getAllProductId();
    }

    @Override
    public double getPrice(String productId) throws SQLException {
        return productDAO.getPrice(productId);
    }
}
