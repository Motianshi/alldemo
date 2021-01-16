package com.anqi.alldemo.demoboottest;

import com.anqi.alldemo.demoboottest.service.TestService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Junit5单元测试")
public class NewTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestService testService;

    @BeforeEach
    void validParam() {
        System.out.println("~验证参数");
    }

    @BeforeAll
    static void buildResources() {
        System.out.println("统一加载资源");
    }

    @AfterAll
    static void clear() {
        System.out.println("~清理资源——释放链接");
    }

    @Test
    @DisplayName("测试获取商品接口")
    void testItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getItems").param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        System.out.println(testService.getItems());
    }

    @Test
    @DisplayName("测试断言equals")
    void testEquals() {
        assertTrue(3 < 4);
    }

    @Test
    @DisplayName("测试断言NotNull")
    void testNotNull() {
        assertNotNull(new Object());
    }

    @Test
    @DisplayName("测试断言抛异常")
    void testThrows() {
        ArithmeticException arithExcep = assertThrows(ArithmeticException.class, () -> {
            int m = 5/0;
        });
        assertEquals("/ by zero", arithExcep.getMessage());
    }

    @Test
    @DisplayName("测试断言超时")
    void testTimeOut() {
        String actualResult = assertTimeout(ofSeconds(2), () -> {
            Thread.sleep(1000);
            return "a result";
        });
        System.out.println(actualResult);
    }

    @Test
    @Disabled
    @DisplayName("disable测试断言equals")
    void testDisableEquals() {
        assertTrue(3 < 4);
    }

    @Test
    @DisplayName("测试组合断言")
    void testAll() {
        assertAll("测试item商品下单",
                () -> {
                    //模拟用户余额扣减
                    assertTrue(1 < 2, "余额不足");
                },
                () -> {
                    //模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    //模拟交易流水落库
                    assertNotNull(new Object());
                }
        );
    }


    @RepeatedTest(3)
    @DisplayName("重复测试")
    void repeatedTest() {
        System.out.println("调用");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("参数化测试")
    void paramTest(int a) {
        assertTrue(a > 0 && a < 4);
    }

    @Nested
    @DisplayName("内嵌订单测试")
    class OrderTestClas {

        @Test
        @DisplayName("取消订单")
        void cancelOrder() {
            int status = -1;
            System.out.println("取消订单成功,订单状态为:"+status);
        }
    }
}
