package me.khrystal.market;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kHRYSTAL on 18/6/6.
 * 配置spring和junit整合 junit启动时加载Spring IOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件的位置 包含dao与service的测试
@ContextConfiguration(locations = {"classpath:/spring/spring-dao.xml", "classpath:/spring/spring-service.xml"})
@Ignore
public class BaseTest {
}
