package me.grigoryan.shopapi.service.impl;

import me.grigoryan.shopapi.entity.ProductInfo;
import me.grigoryan.shopapi.exception.MyException;
import me.grigoryan.shopapi.repository.ProductInfoRepository;
import me.grigoryan.shopapi.service.CategoryService;
import me.grigoryan.shopapi.enums.ProductStatusEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductInfoRepository productInfoRepository;

    @Mock
    private CategoryService categoryService;

    private ProductInfo productInfo;

    @Before
    public void setUp() {
        productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductStock(10);
        productInfo.setProductStatus(1);
    }

    @Test
    public void increaseStockTest() {
        when(productInfoRepository.findByProductId(productInfo.getProductId())).thenReturn(productInfo);

        productService.increaseStock("1", 10);

        Mockito.verify(productInfoRepository, Mockito.times(1)).save(productInfo);
    }

    @Test
    public void decreaseStockTest() {
        when(productInfoRepository.findByProductId(productInfo.getProductId())).thenReturn(productInfo);

        productService.decreaseStock("1", 9);

        Mockito.verify(productInfoRepository, Mockito.times(1)).save(productInfo);
    }


    @Test
    public void offSaleTest() {
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());

        when(productInfoRepository.findByProductId(productInfo.getProductId())).thenReturn(productInfo);

        productService.offSale("1");

        Mockito.verify(productInfoRepository, Mockito.times(1)).save(productInfo);
    }





    @Test
    public void onSaleTest() {
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());

        when(productInfoRepository.findByProductId(productInfo.getProductId())).thenReturn(productInfo);

        productService.onSale("1");

        Mockito.verify(productInfoRepository, Mockito.times(1)).save(productInfo);
    }



    @Test
    public void updateProductTest() {
        productService.update(productInfo);

        Mockito.verify(productInfoRepository, Mockito.times(1)).save(productInfo);
    }



    @Test
    public void deleteTest() {
        when(productInfoRepository.findByProductId(productInfo.getProductId())).thenReturn(productInfo);

        productService.delete("1");

        Mockito.verify(productInfoRepository, Mockito.times(1)).delete(productInfo);
    }


}
