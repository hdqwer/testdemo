

package com.bitzoom;

import com.wedu.modules.sys.dao.GoodsDao;
import com.wedu.service.DynamicDataSourceTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 多数据源测试
 *
 * @author wedu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DynamicDataSourceTestService dynamicDataSourceTestService;
    @Autowired
    private GoodsDao goodsDao;

//    @Test
//    public void test(){
//        Long id = 1L;
//
//        dynamicDataSourceTestService.updateUser(id);
//        dynamicDataSourceTestService.updateUserBySlave1(id);
//        dynamicDataSourceTestService.updateUserBySlave2(id);
//    }
//    @Test
//    public void test1(){
//        System.out.println(goodsDao.getAll());
//    }

}
